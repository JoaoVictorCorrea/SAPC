package br.com.sapc.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import br.com.sapc.entities.SolicitacaoDeEntrada;
import jakarta.validation.Valid;

public interface SolicitacaoDeEntradaService {

	void criarSolicitacao(@Valid SolicitacaoDeEntrada solicitacaoDeEntrada);

	boolean existeSolicitacaoPendente(Long id);

	Page<SolicitacaoDeEntrada> findAllWithSort(String field, String direction, int pageNumber);

	Sort getSortByField(String field, String direction);

	Page<SolicitacaoDeEntrada> findPage(int pageNumber);

	void delete(SolicitacaoDeEntrada solicitacaoDeEntrada);

	Optional<SolicitacaoDeEntrada> findById(Long id);
}
