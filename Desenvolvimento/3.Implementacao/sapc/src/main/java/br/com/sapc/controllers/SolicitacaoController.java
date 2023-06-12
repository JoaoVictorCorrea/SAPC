package br.com.sapc.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sapc.entities.SolicitacaoDeEntrada;
import br.com.sapc.entities.UsuarioExterno;
import br.com.sapc.services.SolicitacaoDeEntradaService;
import br.com.sapc.services.UsuarioExternoService;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/solicitacao")
public class SolicitacaoController {

	@Autowired
	private SolicitacaoDeEntradaService solicitacaoDeEntradaService;
	
	@Autowired
	private UsuarioExternoService usuarioExternoService;
	
	@GetMapping
	public String getAllPages(Model model){ 
	    return  getOnePage(1 , model); 
	}
	
	@GetMapping("/page/{pageNumber}")
	public String getOnePage(@PathVariable("pageNumber") int currentPage,  Model model) {
		Page<SolicitacaoDeEntrada> page = solicitacaoDeEntradaService.findPage(currentPage);
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		List<SolicitacaoDeEntrada> listaSolicitacoes = page.getContent();
		List<UsuarioExterno> listUsuarioExterno = usuarioExternoService.findAll();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("listaSolicitacoes", listaSolicitacoes);
		model.addAttribute("listUsuarioExterno", listUsuarioExterno);
		return "solicitacao/solicitacao";
	}
	
	@GetMapping("/page/{pageNumber}/{field}")
	public String getPageWithSort(Model model,
			@PathVariable("pageNumber") int currentPage,
			@PathVariable("field") String field,
			@PathParam("sortDir") String sortDir) {
		
		if (sortDir == null || !sortDir.matches("(asc|desc)")) {
	        sortDir = "asc";
	    }
		
		Page<SolicitacaoDeEntrada> page = solicitacaoDeEntradaService.findAllWithSort(field, sortDir, currentPage);
		List<SolicitacaoDeEntrada> listaSolicitacoes = page.getContent();
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		List<UsuarioExterno> listUsuarioExterno = usuarioExternoService.findAll();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listaSolicitacoes", listaSolicitacoes);
//		model.addAttribute("tipoUsuarioExterno", Arrays.asList(TipoUsuarioExterno.values()));
		model.addAttribute("listUsuarioExterno", listUsuarioExterno);
		return "/solicitacao/solicitacao";
	}
	
	@GetMapping("/deletar/{id}")
    public String deletarUsuarioExterno(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {
        Optional<SolicitacaoDeEntrada> solicitacaoOptional = solicitacaoDeEntradaService.findById(id);
        if(!solicitacaoOptional.isPresent()) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao deletar!");
            return "redirect:/solicitacao?erro";
        }
        solicitacaoDeEntradaService.delete(solicitacaoOptional.get());
        redirectAttributes.addFlashAttribute("mensagem", "Deletado com sucesso!");
        return "redirect:/solicitacao?success";
    }
	
}
