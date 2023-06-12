package br.com.sapc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sapc.entities.Fluxo;

@Repository
public interface FluxoRepository extends JpaRepository<Fluxo, Long>{

}
