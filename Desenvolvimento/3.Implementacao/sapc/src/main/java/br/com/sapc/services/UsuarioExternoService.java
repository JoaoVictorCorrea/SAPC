
package br.com.sapc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import br.com.sapc.entities.UsuarioExterno;
import jakarta.validation.Valid;

public interface UsuarioExternoService {

	List<UsuarioExterno> findAll();

	Page<UsuarioExterno> findPage(int pageNumber);

	Page<UsuarioExterno> findAllWithSort(String field, String direction, int pageNumber);

	Sort getSortByField(String field, String direction);

	void adicionarUsuarioExterno(@Valid UsuarioExterno usuarioExterno);
	
	Optional<UsuarioExterno> findById(Long id);
	
	void delete(UsuarioExterno usuarioExterno);

	List<String> buscarNomes(String valor);

	UsuarioExterno findByNome(String nome);
}