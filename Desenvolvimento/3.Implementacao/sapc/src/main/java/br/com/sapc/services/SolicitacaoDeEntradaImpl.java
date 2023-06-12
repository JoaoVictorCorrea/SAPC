package br.com.sapc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;

import br.com.sapc.entities.SolicitacaoDeEntrada;
import br.com.sapc.repositories.SolicitacaoDeEntradaRepository;
import jakarta.validation.Valid;

@Service
public class SolicitacaoDeEntradaImpl implements SolicitacaoDeEntradaService{

	@Autowired
	private SolicitacaoDeEntradaRepository solicitacaoDeEntradaRepository;
	
	@Override
	public void criarSolicitacao(@Valid SolicitacaoDeEntrada solicitacaoDeEntrada) {
		solicitacaoDeEntradaRepository.save(solicitacaoDeEntrada);
	}

	@Override
	public boolean existeSolicitacaoPendente(Long id) {
		return solicitacaoDeEntradaRepository.qtdSolicitacoesPendentes(id) == 0 ? false : true;
	}

	@Override
	public Page<SolicitacaoDeEntrada> findPage(int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber -1, 5);
		return solicitacaoDeEntradaRepository.findAll(pageable);
	}
	
	@Override
	public Page<SolicitacaoDeEntrada> findAllWithSort(String field, String direction, int pageNumber){
		Sort sort = getSortByField(field, direction);
		Pageable pageable = PageRequest.of(pageNumber -1, 5, sort);
		return solicitacaoDeEntradaRepository.findAll(pageable);		
	}
	
	@Override
	public Sort getSortByField(String field, String direction) {
		
	    if(field.equalsIgnoreCase("nome")) {
	    	return direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
	    			JpaSort.unsafe("morador.nome").ascending() : JpaSort.unsafe("morador.nome").descending();
	    } else if(field.equalsIgnoreCase("usuarioExterno")) {
	    	return direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
	    			JpaSort.unsafe("usuarioExterno.nome").ascending() : JpaSort.unsafe("usuarioExterno.nome").descending();
		} else {
			return direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
					Sort.by(field).ascending() : Sort.by(field).descending();
		}
		
	}
	
	@Override
    public void delete(SolicitacaoDeEntrada solicitacaoDeEntrada) {
        solicitacaoDeEntradaRepository.delete(solicitacaoDeEntrada);
    }
	
	@Override
    public Optional<SolicitacaoDeEntrada> findById(Long id) {
        return solicitacaoDeEntradaRepository.findById(id);
    }
}
