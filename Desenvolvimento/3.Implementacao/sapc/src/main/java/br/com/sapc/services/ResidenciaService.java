package br.com.sapc.services;

import java.util.Optional;

import br.com.sapc.entities.Residencia;
import br.com.sapc.enums.TipoBloco;

public interface ResidenciaService {

	Optional<Residencia> findById(Long id);

	Optional<Residencia> findByResidenciaNumeroBloco(Integer numero, TipoBloco bloco);

}
