package br.com.sapc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;

import br.com.sapc.entities.Fluxo;
import br.com.sapc.repositories.FluxoRepository;

@Service
public class FluxoServiceImpl implements FluxoService{

	@Autowired
	private FluxoRepository fluxoRepository;
	
	@Override
	public Page<Fluxo> findPage(int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber -1, 5);
		return fluxoRepository.findAll(pageable);
	}
	
	@Override
	public Page<Fluxo> findAllWithSort(String field, String direction, int pageNumber){
		Sort sort = getSortByField(field, direction);
		Pageable pageable = PageRequest.of(pageNumber -1, 5, sort);
		return fluxoRepository.findAll(pageable);		
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
	public Optional<Fluxo> findById(Long id) {
		return fluxoRepository.findById(id);
	}

	@Override
	public void save(Fluxo fluxo) {
		fluxoRepository.save(fluxo);
	}
}
