package br.com.fight.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import br.com.fight.Entity.Academia;
import br.com.fight.Entity.Lutador;
import br.com.fight.Service.LutadorService;
import br.com.fight.VO.LutadorVO;
import br.com.fight.Repository.AcademiaRepository;
import br.com.fight.Repository.LutadorRepository;

@Service
public class LutadorServiceImpl implements LutadorService {

    @Autowired
    private LutadorRepository lutadorRepository;

    @Autowired
    AcademiaRepository academiaRepository;

    public Lutador toEntity(LutadorVO vo) {
        Lutador newLutador = new Lutador();
        newLutador.setNome(vo.getNome());
        newLutador.setGenero(vo.getGenero());
        newLutador.setIdade(vo.getIdade());
        newLutador.setGraduacao(vo.getGraduacao());
        newLutador.setDisponivelLuta(vo.getDisponivelLuta());
  // Buscar a academia associada pelo ID
        Optional<Academia> academia = academiaRepository.findById(vo.getIdAcademia());
        academia.ifPresent(newLutador::setAcademia); // Se encontrada, associa a academia ao lutador

        return newLutador;
    }
        
    

    public Lutador updateToEntity(LutadorVO vo, Lutador lutador) {
        lutador.setIdade(vo.getIdade());
        lutador.setGraduacao(vo.getGraduacao());
        lutador.setDisponivelLuta(vo.getDisponivelLuta());

        return lutador;
    }


    @Override
    public List<Lutador> findAll() {
        return lutadorRepository.findAll();
    }
    @Override
    public Optional<Lutador> findByLutador(Long id) {
        return lutadorRepository.findById(id);
    }

    @Override
    public List<Lutador> findAllByGenero(String genero) {

        return lutadorRepository.findByGenero(genero);

    }

    @Override
    public Lutador adicionarLutador(LutadorVO entity) {

        Lutador newLutador = toEntity(entity);
        return lutadorRepository.save(newLutador);
    }

    @Override
    public Lutador updateLutador(Long id, LutadorVO entity) {
        Optional<Lutador> lutador = lutadorRepository.findById(id);
        if (lutador.isPresent()) {
            return lutadorRepository.save(updateToEntity(entity, lutador.get()));
        }
        return null;
    }

    @Override
    public boolean removerLutador(Long id) {
        Optional<Lutador> lutador = lutadorRepository.findById(id);
        if (lutador.isPresent()) {
            lutadorRepository.delete(lutador.get());
            return true;
        }
        return false;
    }

    

    @Override
    public List<Lutador> casarLutas(Long lutadorId) {
        Lutador lutadorBase = lutadorRepository.findById(lutadorId)
                .orElseThrow(() -> new RuntimeException("Lutador não encontrado"));
        
        List<Lutador> lutadoresCompativeis = new ArrayList<>();
        List<Lutador> todosLutadores = lutadorRepository.findAll();

        for (Lutador lutador : todosLutadores){
            if (!lutador.getId().equals(lutadorBase.getId()) &&
                lutador.getAcademia().getArteMarcial().equals(lutadorBase.getAcademia().getArteMarcial()) &&
                lutador.getGenero().equals(lutadorBase.getGenero()) &&
                verificarCompatibilidade(lutadorBase, lutador)){

                lutadoresCompativeis.add(lutador);
            }
        }

        return lutadoresCompativeis;
    }

    @Override
    public boolean verificarCompatibilidade(Lutador lutadorBase, Lutador lutador) {
        int idadeBase = lutadorBase.getIdade();
        int idadeOutro = lutador.getIdade();
        double pesoBase = lutadorBase.getPeso();
        double pesoOutro = lutador.getPeso();
        
        // Categoria Infantil
        if (idadeBase <= 11 && idadeOutro <= 11){
            boolean idadeCompativel = Math.abs(idadeOutro - idadeBase) <= 1;
            return idadeCompativel && verificarFaixaPesoInfantil(pesoBase, pesoOutro);
        }

        // Categoria Juvenil
        else if (idadeBase <= 17 && idadeOutro <= 17){ 
            boolean idadeCompativel = Math.abs(idadeOutro - idadeBase) <= 2;
            boolean pesoCompativel = verificarFaixaPesoJuvenil(pesoBase, pesoOutro);
            return idadeCompativel && pesoCompativel;
        }

        // Categoria Adulto
        else if (idadeBase <= 30 && idadeOutro <= 30){ 
            boolean idadeCompativel = Math.abs(idadeOutro - idadeBase) <= 2;
            boolean pesoCompativel = verificarFaixaPesoAdulto(pesoBase, pesoOutro);
            return idadeCompativel && pesoCompativel;
        }

        // Categoria Master
        else { 
            boolean idadeCompativel = verificarMaster(idadeBase, idadeOutro);
            boolean pesoCompativel = verificarFaixaPesoAdulto(pesoBase, pesoOutro);
            return idadeCompativel && pesoCompativel;
        }
    }
    

    @Override
    public boolean verificarFaixaPesoInfantil(double pesoBase, double pesoOutro){
        return  (pesoBase > 30 && pesoBase <= 35 && pesoOutro > 30 && pesoOutro <= 35) ||
                (pesoBase > 35 && pesoBase <= 40 && pesoOutro > 35 && pesoOutro <= 40) ||
                (pesoBase > 40 && pesoBase <= 45 && pesoOutro > 40 && pesoOutro <= 45) ||
                (pesoBase > 45 && pesoBase <= 50 && pesoOutro > 45 && pesoOutro <= 50) ;
    }
    @Override
    public boolean verificarFaixaPesoJuvenil(double pesoBase, double pesoOutro) {
        return (pesoBase <= 50 && pesoOutro <= 50) ||
               (pesoBase > 50 && pesoBase <= 60 && pesoOutro > 50 && pesoOutro <= 60) ||
               (pesoBase > 60 && pesoBase <= 70 && pesoOutro > 60 && pesoOutro <= 70);
    }
    
    @Override
    public boolean verificarFaixaPesoAdulto(double pesoBase, double pesoOutro) {
        return (pesoBase <= 50 && pesoOutro <= 50) ||
               (pesoBase > 50 && pesoBase <= 60 && pesoOutro > 50 && pesoOutro <= 60) ||
               (pesoBase > 60 && pesoBase <= 70 && pesoOutro > 60 && pesoOutro <= 70);
    }
    @Override
    public boolean verificarMaster(int idadeBase, int idadeOutro) {
        int faixaBase = (idadeBase - 30) / 5;
        int faixaOutro = (idadeOutro - 30) / 5;
        return faixaBase == faixaOutro;
    }





}
