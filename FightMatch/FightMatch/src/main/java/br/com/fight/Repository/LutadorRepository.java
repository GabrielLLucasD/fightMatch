package br.com.fight.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fight.Entity.Lutador;

@Repository
public interface  LutadorRepository extends JpaRepository<Lutador, Long> {
        List<Lutador> findByGenero(String genero);

}
