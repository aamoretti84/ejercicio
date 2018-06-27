package com.meli.examen.mutant.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DnaDTO {
	
	@Id
	private String[] dna;
	private boolean isMutant;
	
	public DnaDTO() {
		
	}
	
	public void setDna(String[] dna) {
		this.dna = dna;
	}
	
	public String[] getDna() {
		return this.dna;
	}

	public boolean isMutant() {
		return isMutant;
	}

	public void setMutant(boolean isMutant) {
		this.isMutant = isMutant;
	}
}
