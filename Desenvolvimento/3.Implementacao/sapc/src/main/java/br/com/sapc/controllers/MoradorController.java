package br.com.sapc.controllers;

import java.time.LocalDateTime;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sapc.entities.Morador;
import br.com.sapc.entities.SolicitacaoDeEntrada;
import br.com.sapc.entities.UsuarioExterno;
import br.com.sapc.enums.StatusSolicitacao;
import br.com.sapc.enums.TipoBloco;
import br.com.sapc.enums.TipoUsuarioExterno;
import br.com.sapc.services.MoradorService;
import br.com.sapc.services.ResidenciaService;
import br.com.sapc.services.SolicitacaoDeEntradaService;
import br.com.sapc.services.UsuarioExternoService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/morador")
public class MoradorController {
	
	@Autowired
	private ResidenciaService residenciaService;

	@Autowired
	private UsuarioExternoService usuarioExternoService;
	
	@Autowired
	private SolicitacaoDeEntradaService solicitacaoDeEntradaService;
	
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
		List<UsuarioExterno> listUsuarioExterno = usuarioExternoService.findAll();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("listMoradores", listMoradores);
		model.addAttribute("bloco", Arrays.asList(TipoBloco.values()));
		model.addAttribute("tipoUsuarioExterno", Arrays.asList(TipoUsuarioExterno.values()));
		model.addAttribute("listUsuarioExterno", listUsuarioExterno);
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
		List<UsuarioExterno> listUsuarioExterno = usuarioExternoService.findAll();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listMoradores", listMoradores);
		model.addAttribute("bloco", Arrays.asList(TipoBloco.values()));
		model.addAttribute("tipoUsuarioExterno", Arrays.asList(TipoUsuarioExterno.values()));
		model.addAttribute("listUsuarioExterno", listUsuarioExterno);
		return "/morador/morador";
	}
	
	@GetMapping("/adicionar")
	public String moradorForm(@ModelAttribute Morador morador, Model model){
		
		model.addAttribute("bloco", Arrays.asList(TipoBloco.values()));
		model.addAttribute("morador", morador);
		return "morador/moradorForm";
	}
	
	@PostMapping("/adicionar")
	public String adicionarMorador(@Valid Morador morador, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			model.addAttribute("bloco", Arrays.asList(TipoBloco.values()));
			return "morador/moradorForm";
		}
		
		moradorService.adicionarMorador(morador);
		redirectAttributes.addFlashAttribute("mensagem", "Cadastrado com sucesso!");
		return "redirect:/morador?success";
	}
	
	@GetMapping("/deletar/{id}")
	public String deletarMorador(@PathVariable(value = "id") Long id) {
		Optional<Morador> moradorOptional = moradorService.findById(id);
		if(!moradorOptional.isPresent()) {
			
		}
		moradorService.delete(moradorOptional.get());
		return "redirect:/morador";
	}
	
	@PostMapping("/editar")
	public String editarMorador(@Valid Morador morador, BindingResult result, RedirectAttributes redirectAttributes) {
		
		Optional<Morador> moradorOptional = moradorService.findById(morador.getId());
		if(moradorOptional.isEmpty() || result.hasErrors()) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao editar!");
			return "redirect:/morador?erro";
		}
		
		moradorOptional.get().setCpf(morador.getCpf());
		moradorOptional.get().setDataNasc(morador.getDataNasc());
		moradorOptional.get().setEmail(morador.getEmail());
		moradorOptional.get().setId(morador.getId());
		moradorOptional.get().setNome(morador.getNome());
		moradorOptional.get().getResidencia().setBloco(morador.getResidencia().getBloco());
		moradorOptional.get().getResidencia().setNumero(morador.getResidencia().getNumero());
		moradorOptional.get().setTelefone(morador.getTelefone());
		moradorOptional.get().setSindico(morador.getSindico());
		
		moradorService.adicionarMorador(moradorOptional.get());
		redirectAttributes.addFlashAttribute("mensagem", "Editado com sucesso!");
		return "redirect:/morador?success";
	}
	
	@PostMapping("/solicitar")
	public String solicitarEntrada(@RequestParam("nome") String nome, 
			@RequestParam("id") Long id, @RequestParam("tipoUsuarioExterno") TipoUsuarioExterno tipoUsuarioExterno, RedirectAttributes redirectAttributes) {
		SolicitacaoDeEntrada solicitacaoDeEntrada = new SolicitacaoDeEntrada();
		UsuarioExterno usuarioExterno = usuarioExternoService.findByNome(nome);
		solicitacaoDeEntrada.setUsuarioExterno(usuarioExterno);
		
		if(!solicitacaoDeEntradaService.existeSolicitacaoPendente(solicitacaoDeEntrada.getUsuarioExterno().getId())){
			solicitacaoDeEntrada.setResidencia(residenciaService.findById(id).get());
			solicitacaoDeEntrada.setDataSolicitacao(LocalDateTime.now());
			solicitacaoDeEntrada.setStatusSolicitacao(StatusSolicitacao.PENDENTE);
			solicitacaoDeEntrada.setTipoUsuarioExterno(tipoUsuarioExterno);
			solicitacaoDeEntradaService.criarSolicitacao(solicitacaoDeEntrada);
			redirectAttributes.addFlashAttribute("mensagem", "Solicitação feita com sucesso!");
			return "redirect:/morador?success";
		}
		
		redirectAttributes.addFlashAttribute("mensagem", "Erro na solicitação!");
		return "redirect:/morador?erro";
	}
	
	@GetMapping("/buscar-usuario-externo")
    @ResponseBody
    public List<String> buscarNomesDosUsuariosExternos(@RequestParam("valor") String valor) {
        List<String> nomes = usuarioExternoService.buscarNomes(valor);
        return nomes;
    }
	
	

}