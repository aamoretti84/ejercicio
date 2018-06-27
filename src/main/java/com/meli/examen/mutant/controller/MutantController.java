package com.meli.examen.mutant.controller;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meli.examen.mutant.dto.DnaDTO;
import com.meli.examen.mutant.service.DnaService;

@RestController
public class MutantController {
	
	@Autowired
	private DnaService dnaService;
	private final Pattern regExPattern = Pattern.compile("^[ACGT]+$");

	/**
	 * Devuelve:
	 * 	200-OK: Si el adn ingresado es mutante
	 *  403-Forbidden: Si el adn ingresado es humano
	 *  400-Bad Request: Si el adn ingresado no es una matriz NxN
	 *  
	 * Si el adn es correcto se almacena en la base de datos
	 * @param dna
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value = "/mutant")
	public ResponseEntity<String> checkIfMutantAndSave(@RequestBody DnaDTO dna) {
		
		if (!isValidDNA(dna.getDna())) {
			return ResponseEntity.status(
					HttpStatus.BAD_REQUEST).body(
					"Invalid DNA sequence. Please enter a NxN table containing appropiate elements.");
		}
				
		boolean isMutant = dnaService.isMutant(dna.getDna());
		dna.setMutant(isMutant);
		dnaService.addDna(dna);
		
		if (isMutant) {
			return ResponseEntity.status(HttpStatus.OK).body("Input DNA is Mutant");
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Input DNA is Human");
		}
			
	}
	
	public DnaService getDnaAnalizer() {
		return dnaService;
	}

	public void setDnaAnalizer(DnaService dnaAnalizer) {
		this.dnaService = dnaAnalizer;
	}

	/**
	 * Verifica si el array de adn es correcto: no nulo, no vacío, no NxN
	 * @param dna
	 * @return
	 */
	private boolean isValidDNA(String[] dna) {
		
		if (dna == null || dna.length == 0 || dna.length != dna[0].length()) {
			return false;
		} 
		
		for (String row : dna) {
			if (!regExPattern.matcher(row).matches()) {
				return false;
			}
		}
		
		return true;
		
	}
	
	
}
