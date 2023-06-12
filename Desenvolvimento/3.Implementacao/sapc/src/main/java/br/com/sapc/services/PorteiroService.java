package br.com.sapc.services;

import br.com.sapc.dtos.PorteiroLoginDto;
import br.com.sapc.entities.Porteiro;

public interface PorteiroService {

	PorteiroLoginDto converterParaPorteiroDto(Porteiro porteiro);
}
