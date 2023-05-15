package br.com.sapc.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sapc.dtos.MoradorDto;
import br.com.sapc.entities.Morador;
import br.com.sapc.enums.TipoBloco;
import br.com.sapc.services.MoradorService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/morador")
public class MoradorController {

	@Autowired
	private MoradorService moradorService;
	
	@GetMapping
	public String getAllPages(Model model){ 
	    return  getOnePage(1 , model); 
	}
	
	@GetMapping("/page/{pageNumber}")
	public String getOnePage(@PathVariable("pageNumber") int currentPage,  Model model) {
		Page<Morador> page = moradorService.findPage(currentPage);
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		List<Morador> listMoradores = page.getContent(); 		
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("listMoradores", listMoradores);
		return "/morador/morador";
	}
	
	@GetMapping("/page/{pageNumber}/{field}")
	public String getPageWithSort(Model model,
			@PathVariable("pageNumber") int currentPage,
			@PathVariable("field") String field,
			@PathParam("sortDir") String sortDir) {
		
		if (sortDir == null || !sortDir.matches("(asc|desc)")) {
	        sortDir = "asc";
	    }
		
		Page<Morador> page = moradorService.findAllWithSort(field, sortDir, currentPage);
		List<Morador> listMoradores = page.getContent();
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listMoradores", listMoradores);
		return "/morador/morador";
	}
	
	@GetMapping("/adicionar")
	public String moradorForm(Model model){
		
		List<TipoBloco> blocos = moradorService.findAllBlocos();
		model.addAttribute("bloco", blocos);
		return "morador/moradorForm";
	}
	
	@PostMapping("/adicionar")
	public String adicionarMorador(@Valid MoradorDto moradorDto, BindingResult result) {
		
		if(result.hasErrors()) {
			return "redirect:/morador";
		}
		
		moradorService.adicionarMorador(moradorDto);
		return "morador/moradorForm";
	}
	
	@GetMapping("/deletar/{id}")
	public String deletarMorador(@PathVariable(value = "id") Long id) {
		Optional<Morador> moradorOptional = moradorService.findById(id);
		if(!moradorOptional.isPresent()) {
			
		}
		moradorService.delete(moradorOptional.get());
		return "redirect:/morador";
	}
	
	
	
	
	
	

}