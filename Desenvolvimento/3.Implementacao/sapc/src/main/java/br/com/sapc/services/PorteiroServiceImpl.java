package br.com.sapc.services;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sapc.dtos.PorteiroLoginDto;
import br.com.sapc.entities.Porteiro;


public class PorteiroServiceImpl implements PorteiroService{
	
	@Autowired
	public PorteiroLoginDto converterParaPorteiroDto(Porteiro porteiro) {
		return new PorteiroLoginDto(porteiro);
	}
}
