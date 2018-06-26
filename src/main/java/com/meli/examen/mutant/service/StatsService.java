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
		
	public StatsDTO getStats() {
		return processStatistics(dnaService.getAllDna());
	}

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
