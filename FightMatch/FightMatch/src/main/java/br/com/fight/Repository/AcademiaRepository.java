package br.com.fight.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fight.Entity.Academia;

@Repository
public interface AcademiaRepository extends JpaRepository<Academia, Long> {

}
