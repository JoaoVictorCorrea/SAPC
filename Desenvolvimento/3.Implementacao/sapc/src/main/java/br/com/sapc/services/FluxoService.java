package br.com.sapc.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import br.com.sapc.entities.Fluxo;

public interface FluxoService {

	Page<Fluxo> findPage(int pageNumber);

	Page<Fluxo> findAllWithSort(String field, String direction, int pageNumber);

	Sort getSortByField(String field, String direction);

	Optional<Fluxo> findById(Long id);

	void save(Fluxo fluxo);

}
