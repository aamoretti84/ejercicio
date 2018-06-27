package com.meli.examen.mutant.bussines.strategy;

public abstract class PatternMatcher {
	protected String[] dna;
	/**
	 * Valores máximo(N-1) que puede tomar el índice
	 */
	protected int maxPosition;
	
	/**
	 * Valor mínimo(0) que puede tomar el índice
	 */
	protected int minPosition;
	
	/**
	 * Longitud del patrón a detectar: lo pedido fueron 4 letras
	 */
	protected static final int PATTERN_LENGHT = 4;
	
	/**
	 * Cantidad de patrones a detectar: lo pedido fueron 2 para
	 * detectar que es mutante
	 */
	protected static final int REQUIRED_MATCHES = 2;
	
	/**
	 * Analiza la matriz y determina si cumple con las condiciones
	 * @return
	 */
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