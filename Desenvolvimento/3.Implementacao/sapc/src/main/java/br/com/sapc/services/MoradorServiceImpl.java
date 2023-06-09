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

import br.com.sapc.entities.Morador;
import br.com.sapc.repositories.MoradorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class MoradorServiceImpl implements MoradorService{
	
	@Autowired
	private MoradorRepository moradorRepository;
	
	@Override
	public List<Morador> findAll() {
		return moradorRepository.findAll();
	}
	
	@Override
	public Page<Morador> findPage(int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber -1, 5);
		return moradorRepository.findAll(pageable);
	}
	
	@Override
	public Page<Morador> findAllWithSort(String field, String direction, int pageNumber){
		Sort sort = getSortByField(field, direction);
		Pageable pageable = PageRequest.of(pageNumber -1, 5, sort);
		return moradorRepository.findAll(pageable);		
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
	@Transactional
	public void adicionarMorador(@Valid Morador morador) {
		moradorRepository.save(morador);
	}
	
	@Override
	public Optional<Morador> findById(Long id) {
		return moradorRepository.findById(id);
	}

	@Transactional
	@Override
	public void delete(Morador morador) {
		moradorRepository.delete(morador);
	}

	@Override
	public List<String> buscarNomes(String valor) {
		return moradorRepository.buscarNomesPorValor(valor);
	}

	@Override
	public Morador findByNome(String nome) {
		return moradorRepository.findByNome(nome);
	}
}