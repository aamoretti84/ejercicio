package com.meli.examen.mutant.dao;

import org.springframework.data.repository.CrudRepository;

import com.meli.examen.mutant.dto.DnaDTO;

public interface DnaDAO extends CrudRepository<DnaDTO, String[]> {
	
	
	
}
