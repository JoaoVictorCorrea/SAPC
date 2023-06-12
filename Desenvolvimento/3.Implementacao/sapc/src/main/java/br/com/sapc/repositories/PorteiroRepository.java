package br.com.sapc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sapc.entities.Porteiro;

public interface PorteiroRepository extends JpaRepository<Porteiro, Long>{

	Optional<Porteiro> findByEmail(String email);
	
}
