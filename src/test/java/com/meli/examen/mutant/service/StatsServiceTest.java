package com.meli.examen.mutant.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.meli.examen.mutant.dto.DnaDTO;
import com.meli.examen.mutant.dto.StatsDTO;

public class StatsServiceTest {
	
	private DnaService dnaService;
	private StatsService statsService;
	private DnaDTO humanDna;
	private DnaDTO mutantDna;
	private List<DnaDTO> dnaBank;
	
	@Before
	public void setUp() {
		dnaService = Mockito.mock(DnaService.class);
		statsService = new StatsService();
		statsService.setDnaSevice(dnaService);
		humanDna = Mockito.mock(DnaDTO.class);
		mutantDna = Mockito.mock(DnaDTO.class);
		when(humanDna.isMutant()).thenReturn(false);
		when(mutantDna.isMutant()).thenReturn(true);
		dnaBank = new ArrayList<DnaDTO>();
	}
	
	@Test
	public void testRatio_noData_nullRatio() {
		
		when(dnaService.getAllDna()).thenReturn(dnaBank);
		
		StatsDTO stats = statsService.getStats();
		
		assertEquals(stats.getCount_human_dna(),0);
		assertEquals(stats.getCount_mutant_dna(),0);
		assertNull(stats.getRatio());
		
	}
	
	@Test
	public void testRatio_noHumans_nullRatio() {
		
		dnaBank.add(mutantDna);
		
		when(dnaService.getAllDna()).thenReturn(dnaBank);
		
		StatsDTO stats = statsService.getStats();
		
		assertEquals(stats.getCount_human_dna(),0);
		assertEquals(stats.getCount_mutant_dna(),1);
		assertNull(stats.getRatio());
		
	}
	
	@Test
	public void testRatio_ratio_1() {
		
		for (int i = 0;i<40;i++) {
			dnaBank.add(mutantDna);
		}
		
		for (int i = 0;i<40;i++) {
			dnaBank.add(humanDna);
		}
		
		when(dnaService.getAllDna()).thenReturn(dnaBank);
		
		StatsDTO stats = statsService.getStats();
		
		assertEquals(stats.getCount_human_dna(),40);
		assertEquals(stats.getCount_mutant_dna(),40);
		assertEquals(BigDecimal.ONE.setScale(1), stats.getRatio());
		
	}
	
	@Test
	public void testRatio_ratio_point4() {
		
		for (int i = 0;i<40;i++) {
			dnaBank.add(mutantDna);
		}
		
		for (int i = 0;i<100;i++) {
			dnaBank.add(humanDna);
		}
		
		when(dnaService.getAllDna()).thenReturn(dnaBank);
		
		StatsDTO stats = statsService.getStats();
		
		assertEquals(stats.getCount_human_dna(),100);
		assertEquals(stats.getCount_mutant_dna(),40);
		assertEquals(BigDecimal.valueOf(4).movePointLeft(1).setScale(1), stats.getRatio());
		
	}
	
}
