package com.meli.examen.mutant.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.meli.examen.mutant.TestHelper;
import com.meli.examen.mutant.dto.DnaDTO;
import com.meli.examen.mutant.service.DnaService;

public class MutantControllerTest {
	
	private MutantController mutantController;
	private DnaService dnaService;
	
	private DnaDTO dna;
		
	@Before
	public void setUp() throws Exception {
		dna = Mockito.mock(DnaDTO.class);
		dnaService = Mockito.mock(DnaService.class);
		mutantController = new MutantController();
		mutantController.setDnaAnalizer(dnaService);
	}

	@Test
	public void checkIfMutantAndSave_invalidDna_null() {
		
		dna.setDna(null);
		ResponseEntity<String> response = mutantController.checkIfMutantAndSave(dna);
		
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void checkIfMutantAndSave_invalidDna_empty() {
		
		dna.setDna(TestHelper.EMPTY_DNA);
		ResponseEntity<String> response = mutantController.checkIfMutantAndSave(dna);
		
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void checkIfMutantAndSave_invalidDna_notNxN() {
		
		dna.setDna(TestHelper.NOT_NXN_DNA);
		ResponseEntity<String> response = mutantController.checkIfMutantAndSave(dna);
		
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void checkIfMutantAndSave_invalidDna_wrongBaseChar() {
		
		dna.setDna(TestHelper.INVALID_CHAR_DNA);
		ResponseEntity<String> response = mutantController.checkIfMutantAndSave(dna);
		
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void checkIfMutantAndSave_validDna_notMutant() {
		when(dna.getDna()).thenReturn(TestHelper.HUMAN_DNA);
		when(dnaService.isMutant(dna.getDna())).thenReturn(false);

		ResponseEntity<String> response = mutantController.checkIfMutantAndSave(dna);
		
		assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}
	
	@Test
	public void checkIfMutantAndSave_validDna_mutant() {
		when(dna.getDna()).thenReturn(TestHelper.HUMAN_DNA);
		when(dnaService.isMutant(dna.getDna())).thenReturn(true);

		ResponseEntity<String> response = mutantController.checkIfMutantAndSave(dna);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
