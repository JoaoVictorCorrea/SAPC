package br.com.sapc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sapc.entities.Morador;

@Repository
public interface MoradorRepository extends JpaRepository<Morador, Long>{
	
}
