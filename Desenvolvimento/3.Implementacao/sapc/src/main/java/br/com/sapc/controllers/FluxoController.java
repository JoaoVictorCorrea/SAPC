package br.com.sapc.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sapc.entities.Fluxo;
import br.com.sapc.entities.Morador;
import br.com.sapc.entities.UsuarioExterno;
import br.com.sapc.services.FluxoService;
import br.com.sapc.services.MoradorService;
import br.com.sapc.services.UsuarioExternoService;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/fluxo")
public class FluxoController {
	
	@Autowired
	private UsuarioExternoService usuarioExternoService;
	
	@Autowired
	private MoradorService moradorService;
	
	@Autowired
	private FluxoService fluxoService;
	
	@GetMapping
	public String getAllPages(Model model){ 
	    return  getOnePage(1 , model); 
	}
	
	@GetMapping("/page/{pageNumber}")
	public String getOnePage(@PathVariable("pageNumber") int currentPage,  Model model) {
		Page<Fluxo> page = fluxoService.findPage(currentPage);
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		List<Fluxo> listaFluxos = page.getContent();
		List<UsuarioExterno> listUsuarioExterno = usuarioExternoService.findAll();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("listaFluxos", listaFluxos);
		model.addAttribute("listUsuarioExterno", listUsuarioExterno);
		return "fluxo/fluxo";
	}
	
	@GetMapping("/page/{pageNumber}/{field}")
	public String getPageWithSort(Model model,
			@PathVariable("pageNumber") int currentPage,
			@PathVariable("field") String field,
			@PathParam("sortDir") String sortDir) {
		
		if (sortDir == null || !sortDir.matches("(asc|desc)")) {
	        sortDir = "asc";
	    }
		
		Page<Fluxo> page = fluxoService.findAllWithSort(field, sortDir, currentPage);
		List<Fluxo> listaFluxos = page.getContent();
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		List<UsuarioExterno> listUsuarioExterno = usuarioExternoService.findAll();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listaFluxos", listaFluxos);
//		model.addAttribute("tipoUsuarioExterno", Arrays.asList(TipoUsuarioExterno.values()));
		model.addAttribute("listUsuarioExterno", listUsuarioExterno);
		return "/fluxo/fluxo";
	}
	
	@GetMapping("/adicionar-saida/{id}")
	public String adicionarSaida(@PathVariable("id") Long id) {
		Optional<Fluxo> optionalFluxo = fluxoService.findById(id);
		optionalFluxo.get().setDataSaida(LocalDateTime.now());
		
		fluxoService.save(optionalFluxo.get());
		return "redirect:/fluxo";
	}
	
	@GetMapping("/buscar-moradores")
    @ResponseBody
    public List<String> buscarNomesDosMoradores(@RequestParam("valor") String valor) {
        List<String> nomes = moradorService.buscarNomes(valor);
        return nomes;
    }
	
	@PostMapping("/adicionar")
	public String adicionarFluxoMorador(@RequestParam("nome") String nome, @RequestParam("placaVeiculo") String placaVeiculo, RedirectAttributes redirectAttributes) {
		Fluxo fluxo = new Fluxo();
		Morador morador = moradorService.findByNome(nome);
		fluxo.setMorador(morador);
		fluxo.setPlacaVeiculo(placaVeiculo);
		fluxo.setDataEntrada(LocalDateTime.now());
		fluxoService.save(fluxo);
		
//		if(!solicitacaoDeEntradaService.existeSolicitacaoPendente(solicitacaoDeEntrada.getUsuarioExterno().getId())){
//			solicitacaoDeEntrada.setResidencia(residenciaService.findById(id).get());
//			solicitacaoDeEntrada.setDataSolicitacao(LocalDateTime.now());
//			solicitacaoDeEntrada.setStatusSolicitacao(StatusSolicitacao.PENDENTE);
//			solicitacaoDeEntrada.setTipoUsuarioExterno(tipoUsuarioExterno);
//			solicitacaoDeEntradaService.criarSolicitacao(solicitacaoDeEntrada);
//			redirectAttributes.addFlashAttribute("mensagem", "Solicitação feita com sucesso!");
//			return "redirect:/morador?success";
//		}
		
		redirectAttributes.addFlashAttribute("mensagem", "Fluxo Adicionado!");
		return "redirect:/fluxo?success";
	}
	
}