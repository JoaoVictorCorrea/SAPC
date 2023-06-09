
package br.com.sapc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import br.com.sapc.entities.Morador;
import jakarta.validation.Valid;

public interface MoradorService {

	List<Morador> findAll();

	Page<Morador> findPage(int pageNumber);

	Page<Morador> findAllWithSort(String field, String direction, int pageNumber);

	Sort getSortByField(String field, String direction);

	void adicionarMorador(@Valid Morador morador);
	
	Optional<Morador> findById(Long id);
	
	void delete(Morador morador);

	List<String> buscarNomes(String valor);

	Morador findByNome(String nome);
}