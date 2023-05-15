package br.com.sapc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sapc.entities.Morador;
import br.com.sapc.enums.TipoBloco;

@Repository
public interface MoradorRepository extends JpaRepository<Morador, Long>{

	@Query("SELECT DISTINCT r.bloco FROM Residencia r WHERE r.id IN (SELECT m.residencia FROM Morador m)")
	List<TipoBloco> findAllBlocos();
	
}
