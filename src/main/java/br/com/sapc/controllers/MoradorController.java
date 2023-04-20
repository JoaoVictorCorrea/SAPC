package br.com.sapc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sapc.entities.Morador;
import br.com.sapc.services.MoradorService;

@Controller
@RequestMapping("/moradores")
public class MoradorController {

	@Autowired
	private MoradorService moradorService;
	
	public List<Morador> listarMoradores(Model model){
		
		List<Morador> moradores = moradorService.listarMoradores();
		return moradores;
	}
}
