package com.meli.examen.mutant.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.examen.mutant.dto.DnaDTO;
import com.meli.examen.mutant.dto.StatsDTO;

@Service
public class StatsService {
	@Autowired
	private DnaService dnaService;

	
	public StatsService() {
		
	}
	
	/**
	 * Recupera todos los ADN de la base y devuelve las estadísticas
	 * @return
	 */
	public StatsDTO getStats() {
		return processStatistics(dnaService.getAllDna());
	}

	/**
	 * Cuenta la cantidad de humanos y mutantes y devuelve
	 * el ratio
	 * @param dnaBank
	 * @return
	 */
	private StatsDTO processStatistics(List<DnaDTO> dnaBank) {
		
		long mutantCount = 0;
		long humanCount = 0;
		
		for (DnaDTO dna : dnaBank) {
			if (dna.isMutant()) {
				mutantCount++;
			} else {
				humanCount++;
			}
		}
		
		BigDecimal ratio = calculateRatio(mutantCount, humanCount);
		
		return new StatsDTO(mutantCount, humanCount,
				ratio);
		
	}

	/**
	 * Calcula el ratio en base a los totales de adn humano y mutante.
	 * Si no existen registros humanos, el ratio es nulo (indefinido)
	 * @param mutantCount
	 * @param humanCount
	 * @return
	 */
	private BigDecimal calculateRatio(long mutantCount, long humanCount) {
		
		BigDecimal ratio = null;
		
		if (humanCount != 0) {
			ratio = BigDecimal.valueOf(mutantCount)
				.divide(BigDecimal.valueOf(humanCount), 1, BigDecimal.ROUND_HALF_EVEN);
		}
		
		return ratio;
	}
	
	public void setDnaSevice(DnaService dnaService) {
		this.dnaService = dnaService;
	}
}
