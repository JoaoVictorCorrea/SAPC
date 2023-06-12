package br.com.sapc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sapc.entities.Morador;

@Repository
public interface MoradorRepository extends JpaRepository<Morador, Long>{

	@Query("SELECT u.nome FROM Morador u WHERE u.nome LIKE %:valor%")
	List<String> buscarNomesPorValor(@Param("valor") String valor);

	Morador findByNome(String nome);
	
}
