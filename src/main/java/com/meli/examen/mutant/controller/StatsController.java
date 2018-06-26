package com.meli.examen.mutant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meli.examen.mutant.dto.StatsDTO;
import com.meli.examen.mutant.service.DnaService;
import com.meli.examen.mutant.service.StatsService;

@RestController
public class StatsController {
	
	@Autowired
	private StatsService statsService;
	@Autowired
	private DnaService dnaService;
	
	/**
	 * Devuelve un json con la información de la cantidad
	 * de humanos y mutantes en la base, y el ratio
	 */
	@RequestMapping("/stats")
	public StatsDTO getStats() {
		return statsService.getStats();
	}
	
	/**
	 * Permite borrar los registros actuales para reiniciar
	 * el ejercicio.
	 */
	@RequestMapping(method=RequestMethod.DELETE,value="/stats")
	public void deleteAllData() {
		dnaService.deleteAllDna();
	}
}
