package br.com.sapc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sapc.entities.Morador;
import br.com.sapc.repositories.MoradorRepository;
import br.com.sapc.services.MoradorService;

public class MoradorServiceImpl implements MoradorService{

	@Autowired
	private MoradorRepository moradorRepository;
	
	@Override
	public List<Morador> listarMoradores() {
		
		return moradorRepository.findAll();
	}

}
