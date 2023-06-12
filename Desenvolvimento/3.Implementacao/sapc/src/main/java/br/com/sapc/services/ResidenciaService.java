package br.com.sapc.services;

import java.util.Optional;

import br.com.sapc.entities.Residencia;

public interface ResidenciaService {

	Optional<Residencia> findById(Long id);

}
