package com.meli.examen.mutant.bussines.strategy;

public abstract class PatternMatcher {
	protected String[] dna;
	protected int maxPosition;
	protected int minPosition;
	protected static final int PATTERN_LENGHT = 4;
	protected static final int REQUIRED_MATCHES = 2;
	
	public abstract boolean analizeSequence();

	public PatternMatcher() {
		
	}
	public PatternMatcher(String[] dna) {
		
		this.setDna(dna);

	}
	
	public void setDna(String[] dna) {
		this.dna = dna;
		this.maxPosition = dna.length - 1;
		this.minPosition = 0;
	}
	
}