package br.com.sapc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sapc.entities.Residencia;
import br.com.sapc.enums.TipoBloco;

public interface ResidenciaRepository extends JpaRepository<Residencia, Long>{
	
	@Query("SELECT u FROM Residencia u WHERE u.numero = :numero and u.bloco = :bloco")
	Optional<Residencia> findByResidenciaNumeroBloco(@Param("numero") Integer numero, @Param("bloco") TipoBloco bloco);
}
