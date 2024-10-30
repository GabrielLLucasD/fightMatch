package br.com.fight.Service;

import java.util.List;
import java.util.Optional;

import br.com.fight.Entity.Academia;
import br.com.fight.Entity.Lutador;
import br.com.fight.VO.AcademiaVO;

public interface AcademiaService {
    List<Academia> findAll();
    Optional<Academia> findByAcademia(Long id);
    List<Lutador> findAllLutadoresByAcademia(Long academiaId);
    Academia adicionarAcademia(AcademiaVO entity);
    Academia updateAcademia(Long id, AcademiaVO entity);
    boolean  removerAcademia(Long id);
}
