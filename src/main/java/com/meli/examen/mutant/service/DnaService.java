package com.meli.examen.mutant.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.examen.mutant.bussines.strategy.PatternMatcher;
import com.meli.examen.mutant.dao.DnaDAO;
import com.meli.examen.mutant.dto.DnaDTO;

@Service
public class DnaService {
	
	@Autowired
	private PatternMatcher patternMatcher;
	
	@Autowired
	private DnaDAO dnaDAO;
	
	
	public DnaService() {

	}
	
	/**
	 * Utiliza una instancia del patternMatcher para 
	 * verificar si la secuencia es o no mutante.
	 * @param dna
	 * @return
	 */
	public boolean isMutant(String[] dna) {
		
		patternMatcher.setDna(dna);
		return patternMatcher.analizeSequence();
		
	}
	
	public void setPatternMatcher(PatternMatcher patternMatcher) {
		this.patternMatcher = patternMatcher;
	}

	public PatternMatcher getPatternMatcher() {
		return this.getPatternMatcher();
	}
	
	/**
	 * Obtiene el listado de ADN almacenado
	 * @return
	 */
	public List<DnaDTO> getAllDna() {
		List<DnaDTO> dnaList = new ArrayList<DnaDTO>();
		dnaDAO.findAll().forEach(dnaList::add);
		return dnaList;
	}
	
	/**
	 * Agrega el registro indicado a la base si no existe.
	 * @param dna
	 */
	public void addDna(DnaDTO dna) {	
		if (!dnaDAO.existsById(dna.getDna())) {
			dnaDAO.save(dna);
		}
	}
	
	/**
	 * Obtiene DNA por id
	 * @param id
	 * @return
	 */
	public DnaDTO getDnaById(String[] id) {
		return dnaDAO.findById(id).get();
	}
	
	/**
	 * Elimina todos los adn de la base
	 */
	public void deleteAllDna() {
		dnaDAO.deleteAll();
	}

}