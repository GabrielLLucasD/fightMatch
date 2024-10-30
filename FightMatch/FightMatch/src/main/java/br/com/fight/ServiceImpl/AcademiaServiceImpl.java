package br.com.fight.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fight.Entity.Academia;
import br.com.fight.Entity.Lutador;
import br.com.fight.Repository.AcademiaRepository;
import br.com.fight.Service.AcademiaService;
import br.com.fight.VO.AcademiaVO;

@Service
public class AcademiaServiceImpl implements AcademiaService {

    @Autowired
    private AcademiaRepository academiaRepository; 

    public Academia toEntity(AcademiaVO vo) {
        Academia newAcademia = new Academia();
        newAcademia.setNome(vo.getNome());
        newAcademia.setMestre(vo.getMestre());
        newAcademia.setArteMarcial(vo.getArteMarcial());
        newAcademia.setTelefone(vo.getTelefone());
        newAcademia.setEmail(vo.getEmail());

        return newAcademia;
    }
    public Academia updateToEntity(AcademiaVO vo, Academia academia) {
        academia.setNome(vo.getNome());
        academia.setMestre(vo.getMestre());
        academia.setArteMarcial(vo.getArteMarcial());
        academia.setTelefone(vo.getTelefone());
        academia.setEmail(vo.getEmail());
        return academia;
    }
    
    @Override
    public List<Academia> findAll() {
        return academiaRepository.findAll();
    }

    @Override
    public Optional<Academia> findByAcademia(Long id) {
        return academiaRepository.findById(id);
    }

    @Override
    public Academia adicionarAcademia(AcademiaVO entity) {
        Academia newAcademia = toEntity(entity);
        return academiaRepository.save(newAcademia);
    }

    @Override
    public Academia updateAcademia(Long id, AcademiaVO entity) {
        Optional<Academia> optionalAcademia = academiaRepository.findById(id);
        if (optionalAcademia.isPresent()) {
            return academiaRepository.save(updateToEntity(entity, optionalAcademia.get()));
        }
        throw new RuntimeException("Academia não encontrada");
    }

    @Override
    public boolean removerAcademia(Long id) {
        Optional<Academia> academia = academiaRepository.findById(id);
        if (academia.isPresent()) {
            academiaRepository.delete(academia.get());
            return true;
        }
        return false;
    }

    @Override
    public List<Lutador> findAllLutadoresByAcademia(Long academiaId) {
        Optional <Academia> academia = academiaRepository.findById(academiaId);
        if(academia.isPresent()){
            return academia.get().getAlunos();
        }
        throw new RuntimeException("Academia não encontrada");
    }
}
