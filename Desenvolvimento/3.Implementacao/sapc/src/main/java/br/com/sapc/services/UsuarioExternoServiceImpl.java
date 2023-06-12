package br.com.sapc.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;

import br.com.sapc.entities.UsuarioExterno;
import br.com.sapc.repositories.UsuarioExternoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class UsuarioExternoServiceImpl implements UsuarioExternoService{

	@Autowired
	private UsuarioExternoRepository usuarioExternoRepository;
	
	@Override
	public List<UsuarioExterno> findAll() {
		return usuarioExternoRepository.findAll();
	}
	
	@Override
	public Page<UsuarioExterno> findPage(int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber -1, 5);
		return usuarioExternoRepository.findAll(pageable);
	}
	
	@Override
	public Page<UsuarioExterno> findAllWithSort(String field, String direction, int pageNumber){
		Sort sort = getSortByField(field, direction);
		Pageable pageable = PageRequest.of(pageNumber -1, 5, sort);
		return usuarioExternoRepository.findAll(pageable);		
	}
	
	@Override
	public Sort getSortByField(String field, String direction) {
		
	    if(field.equalsIgnoreCase("residencia")) {
	    	return direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
	    			JpaSort.unsafe("residencia.numero").ascending() : JpaSort.unsafe("residencia.numero").descending();
	    } else if(field.equalsIgnoreCase("bloco")) {
	    	return direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
	    			JpaSort.unsafe("residencia.bloco").ascending() : JpaSort.unsafe("residencia.bloco").descending();
		} else {
			return direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
					Sort.by(field).ascending() : Sort.by(field).descending();
		}
		
	}

	@Override
	public void adicionarUsuarioExterno(@Valid UsuarioExterno usuarioExterno) {
		usuarioExternoRepository.save(usuarioExterno);
	}
	
	@Override
	public Optional<UsuarioExterno> findById(Long id) {
		return usuarioExternoRepository.findById(id);
	}

	@Transactional
	@Override
	public void delete(UsuarioExterno usuarioExterno) {
		usuarioExternoRepository.delete(usuarioExterno);
	}

	@Override
	public List<String> buscarNomes(String valor) {
		return usuarioExternoRepository.buscarNomesPorValor(valor);
		
	}

	@Override
	public UsuarioExterno findByNome(String nome) {
		return usuarioExternoRepository.findByNome(nome);
	}


}