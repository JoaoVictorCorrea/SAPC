package br.com.sapc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sapc.entities.UsuarioExterno;

@Repository
public interface UsuarioExternoRepository extends JpaRepository<UsuarioExterno, Long>{

	 @Query("SELECT u.nome FROM UsuarioExterno u WHERE u.nome LIKE %:valor%")
	 List<String> buscarNomesPorValor(@Param("valor") String valor);

	UsuarioExterno findByNome(String nome);

}
