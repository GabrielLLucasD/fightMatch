package br.com.fight.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import br.com.fight.Entity.Lutador;
import br.com.fight.VO.LutadorVO;

@Service
public interface LutadorService {
    
    List<Lutador> findAll();
    List<Lutador> findAllByGenero(String genero);
    Optional<Lutador> findByLutador(Long id);
    Lutador adicionarLutador(LutadorVO entity);
    Lutador updateLutador(Long id, LutadorVO entity);
    boolean removerLutador(Long id);
    boolean verificarCompatibilidade(Lutador lutadorBase, Lutador lutador);
    List<Lutador> casarLutas(Long lutadorId);
    boolean verificarFaixaPesoInfantil(double pesoBase, double pesoOutro);
    boolean verificarFaixaPesoJuvenil(double pesoBase, double pesoOutro);
    boolean verificarFaixaPesoAdulto(double pesoBase, double pesoOutro);
    boolean verificarMaster(int idadeBase, int idadeOutro);
}
