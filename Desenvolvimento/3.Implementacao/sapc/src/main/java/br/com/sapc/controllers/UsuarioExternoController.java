package br.com.sapc.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sapc.entities.UsuarioExterno;
import br.com.sapc.services.UsuarioExternoService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/usuario-externo")
public class UsuarioExternoController {
	
	@Autowired
	private UsuarioExternoService usuarioExternoService;
	
	@GetMapping
	public String getAllPages(Model model){ 
	    return  getOnePage(1 , model); 
	}
	
	@GetMapping("/page/{pageNumber}")
	public String getOnePage(@PathVariable("pageNumber") int currentPage,  Model model) {
		Page<UsuarioExterno> page = usuarioExternoService.findPage(currentPage);
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		List<UsuarioExterno> listUsuariosExternos = page.getContent(); 		
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("listUsuariosExternos", listUsuariosExternos);
		return "/usuarioexterno/usuario-externo";
	}
	
	@GetMapping("/page/{pageNumber}/{field}")
	public String getPageWithSort(Model model,
			@PathVariable("pageNumber") int currentPage,
			@PathVariable("field") String field,
			@PathParam("sortDir") String sortDir) {
		
		if (sortDir == null || !sortDir.matches("(asc|desc)")) {
	        sortDir = "asc";
	    }
		
		Page<UsuarioExterno> page = usuarioExternoService.findAllWithSort(field, sortDir, currentPage);
		List<UsuarioExterno> listUsuariosExternos = page.getContent();
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listUsuariosExternos", listUsuariosExternos);
		return "/usuarioexterno/usuario-externo";
	}
	
	@GetMapping("/adicionar")
	public String moradorForm(@ModelAttribute UsuarioExterno usuarioExterno, Model model){
		
		model.addAttribute("usuarioExterno", usuarioExterno);
		return "usuarioexterno/usuarioExternoForm";
	}
	
	@PostMapping("/adicionar")
	public String adicionarUsuarioExterno(@Valid UsuarioExterno usuarioExterno, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			return "usuarioexterno/usuarioExternoForm";
		}
		
		usuarioExternoService.adicionarUsuarioExterno(usuarioExterno);
		redirectAttributes.addFlashAttribute("mensagem", "Cadastrado com sucesso!");
		return "redirect:/usuario-externo?success";
	}
	
	@GetMapping("/deletar/{id}")
	public String deletarUsuarioExterno(@PathVariable(value = "id") Long id) {
		Optional<UsuarioExterno> moradorOptional = usuarioExternoService.findById(id);
		if(!moradorOptional.isPresent()) {
			
		}
		usuarioExternoService.delete(moradorOptional.get());
		return "redirect:/usuario-externo";
	}
	
	@PostMapping("/editar")
	public String editarUsuarioExterno(@Valid UsuarioExterno usuarioExterno, BindingResult result) {
		
		Optional<UsuarioExterno> usuarioExternoOptional = usuarioExternoService.findById(usuarioExterno.getId());
		if(usuarioExternoOptional.isEmpty() || result.hasErrors()) {
			return "redirect:/fluxo";
		}
		
		usuarioExternoOptional.get().setCpf(usuarioExterno.getCpf());
		usuarioExternoOptional.get().setDataNasc(usuarioExterno.getDataNasc());
		usuarioExternoOptional.get().setEmail(usuarioExterno.getEmail());
		usuarioExternoOptional.get().setId(usuarioExterno.getId());
		usuarioExternoOptional.get().setNome(usuarioExterno.getNome());
		usuarioExternoOptional.get().setEmpresa(usuarioExterno.getEmpresa());
		usuarioExternoOptional.get().setObservacao(usuarioExterno.getObservacao());
		usuarioExternoOptional.get().setTelefone(usuarioExterno.getTelefone());
		
		usuarioExternoService.adicionarUsuarioExterno(usuarioExternoOptional.get());
		return "redirect:/usuario-externo";
	}
}
