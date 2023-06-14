package br.com.sapc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sapc.entities.Residencia;
import br.com.sapc.enums.TipoBloco;
import br.com.sapc.repositories.ResidenciaRepository;

@Service
public class ResidenciaServiceImpl implements ResidenciaService{

	@Autowired
	private ResidenciaRepository residenciaRepository;

	@Override
	public Optional<Residencia> findById(Long id) {
		return residenciaRepository.findById(id);
	}
	
	@Override
	public Optional<Residencia> findByResidenciaNumeroBloco(Integer numero, TipoBloco bloco) {
		return residenciaRepository.findByResidenciaNumeroBloco(numero, bloco);
	}
}
