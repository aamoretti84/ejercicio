package com.meli.examen.mutant.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.meli.examen.mutant.TestHelper;
import com.meli.examen.mutant.dto.DnaDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DnaServiceTest {
	
	@Autowired
	private DnaService dnaService;
	
	@Before
	public void setUp() {
		dnaService.deleteAllDna();
	}
	
	@Test
	public void getAllDna_isEmpty() {
		assertTrue(dnaService.getAllDna().isEmpty());
	}
	
	@Test
	public void addDna() {
		DnaDTO dna = new DnaDTO();
		dna.setDna(TestHelper.HUMAN_DNA);
		dna.setMutant(false);
		
		dnaService.addDna(dna);
		
		DnaDTO dnaDto = dnaService.getDnaById(TestHelper.HUMAN_DNA);
		
		assertNotNull(dnaDto);
		assertArrayEquals(dna.getDna(), TestHelper.HUMAN_DNA);
		assertFalse(dnaDto.isMutant());
	}
	
	@Test
	public void addDna_repeated() {
		DnaDTO dna = new DnaDTO();
		dna.setDna(TestHelper.HUMAN_DNA);
		dna.setMutant(false);
		dnaService.addDna(dna);
		dnaService.addDna(dna);
		
		assertEquals(dnaService.getAllDna().size(), 1);
	}

}
