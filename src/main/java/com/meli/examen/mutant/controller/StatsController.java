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
	
	@RequestMapping("/stats")
	public StatsDTO getStats() {
		return statsService.getStats();
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/stats")
	public void deleteAllData() {
		dnaService.deleteAllDna();
	}
}
