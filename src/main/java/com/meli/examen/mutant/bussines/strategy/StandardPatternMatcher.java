package com.meli.examen.mutant.bussines.strategy;

import org.springframework.stereotype.Component;

@Component
public class StandardPatternMatcher extends PatternMatcher {

	public StandardPatternMatcher() {
		
	}
	public StandardPatternMatcher(String[] dna) {
		super(dna);
	}

	@Override
	public boolean analizeSequence() {
		
		int currentMatches = 0;
		
		int i = 0;
		
		//iteración vertical
		while(i <= maxPosition & currentMatches != REQUIRED_MATCHES) {
						
			int j = 0;
			
			//iteración horizontal
			while (j <= maxPosition && currentMatches != REQUIRED_MATCHES) {
				
				currentMatches += analizeDirectionally(i,j,currentMatches);
				
				j++;
				
			}
			
			i++;
			
		}
		
		return currentMatches == REQUIRED_MATCHES;
	}

	/**
	 * Analiza la base actual en todas las direcciones posibles:
	 * horizontal, vertical, diagonal y diagonal invertido.
	 * Se tiene en cuenta también restricciones para que no matchee 2
	 * veces un patrón de 5, 6 o 7 letras iguales seguidas.
	 * Evita seguir buscando coincidencias en las demás direcciones si
	 * ya encontró 2.
	 * @param i
	 * @param j
	 * @param currentMatches
	 * @return
	 */
	private int analizeDirectionally(int i, int j, int currentMatches) {
		
		char base = dna[i].charAt(j);
		int directionalMatches = 0;
		
		if ((currentMatches + directionalMatches) != REQUIRED_MATCHES 
				&& checkPreviousCellsHorizontally(i, j, base)) {
			
			if (matchesHorizontally(i, j, base)) {
				 directionalMatches ++;
			 }
			
		}
		
		if ((currentMatches + directionalMatches) != REQUIRED_MATCHES
				&& checkPreviousCellsVertically(i, j, base)) {
			
			 if (matchesVertically(i, j, base)) {
				 directionalMatches ++;
			 }
			 
		}
		
		if ((currentMatches + directionalMatches) != REQUIRED_MATCHES
				&& checkPreviousCellsDiagonally_straight(i,j,base)) {
			
			 if (matchesDiagonally_straight(i, j, base)) {
				 directionalMatches ++;
			 }
			 
		}
		
		if ((currentMatches + directionalMatches) != REQUIRED_MATCHES
				&& checkPreviousCellsDiagonally_inverted(i,j,base)) {
			
			 if (matchesDiagonally_inverted(i, j, base)) {
				 directionalMatches ++;
			 }
			 
		}
		
		return directionalMatches;
		
	}
	
	/**
	 * Chequea las posiciones previas en forma horizontal para
	 * asegurarse de no contar dos veces patrones de 5, 6 y 7 letras
	 * @param i
	 * @param j
	 * @param base
	 * @return
	 */
	private boolean checkPreviousCellsHorizontally(int i, int j, char base) {
		
		int delta_j = j-1;
		
		while (delta_j >= minPosition && base == dna[i].charAt(delta_j) 
				&& delta_j > (j-PATTERN_LENGHT)) {
			delta_j--;
		}

		return delta_j == j-1 || (delta_j >= minPosition && dna[i].charAt(delta_j) == base);
	}
	
	/**
	 * Busca patrón de cuatro letras seguidas en forma horizontal
	 * @param i
	 * @param j
	 * @param base
	 * @return
	 */
	private boolean matchesHorizontally(int i, int j, char base) {
		
		int counter = 1;
		int delta_j = j + PATTERN_LENGHT -1;
		
		while (delta_j <= maxPosition && base == dna[i].charAt(delta_j)) {
			
			counter++;

			if (counter == PATTERN_LENGHT) {
				return true;
			}
			
			delta_j--;
			
		}
		
		return false;
	}
	
	/**
	 * Chequea las posiciones previas en forma vertical para
	 * asegurarse de no contar dos veces patrones de 5, 6 y 7 letras
	 * @param i
	 * @param j
	 * @param base
	 * @return
	 */
	private boolean checkPreviousCellsVertically(int i, int j, char base) {
		
		int delta_i = i-1;
		
		while (delta_i >= minPosition && base == dna[delta_i].charAt(j) 
				&& delta_i > (i-PATTERN_LENGHT)) {
			delta_i--;
		}
		
		return delta_i == i-1 || (delta_i >= minPosition && dna[delta_i].charAt(j) == base);
	}
	
	/**
	 * Busca patrón de cuatro letras seguidas en forma vertical
	 * @param i
	 * @param j
	 * @param base
	 * @return
	 */
	private boolean matchesVertically(int i, int j, char base) {
		
		int counter = 1;
		int delta_i = i + PATTERN_LENGHT -1;
		
		while (delta_i <= maxPosition && base == dna[delta_i].charAt(j)) {

			counter++;
			
			if (counter == PATTERN_LENGHT) {
				return true;
			}
			
			delta_i--;
			
		}
		
		return false;
	}
	
	/**
	 * Chequea las posiciones previas en forma diagonal para
	 * asegurarse de no contar dos veces patrones de 5, 6 y 7 letras
	 * @param i
	 * @param j
	 * @param base
	 * @return
	 */
	private boolean checkPreviousCellsDiagonally_straight(int i, int j, char base) {
		int delta_i = i-1;
		int delta_j = j-1;
		
		while (
				(delta_i >= minPosition && delta_j >= minPosition)
				&& base == dna[delta_i].charAt(delta_j) 
				&& (delta_i > (i-PATTERN_LENGHT) && delta_j > (j-PATTERN_LENGHT))){
			delta_i--;
			delta_j--;
		}
		
		return (delta_i == i-1 && delta_j == j-1)
				|| (delta_i >= minPosition && delta_j>= minPosition && dna[delta_i].charAt(delta_j) == base);
	}

	/**
	 * Busca patrón de cuatro letras seguidas en forma diagonal
	 * @param i
	 * @param j
	 * @param base
	 * @return
	 */
	private boolean matchesDiagonally_straight(int i, int j, char base) {
		
		int counter = 1;
		int delta_i = i + PATTERN_LENGHT -1;
		int delta_j = j + PATTERN_LENGHT -1;
		
		while (delta_i <= maxPosition && delta_j <= maxPosition && base == dna[delta_i].charAt(delta_j)) {
			
			counter++;
			
			if (counter == PATTERN_LENGHT) {
				return true;
			} 
			
			delta_i--;
			delta_j--;
		}
		
		return false;
	}
	
	
	/**
	 * Chequea las posiciones previas en forma diagonal invertida para
	 * asegurarse de no contar dos veces patrones de 5, 6 y 7 letras
	 * @param i
	 * @param j
	 * @param base
	 * @return
	 */
	private boolean checkPreviousCellsDiagonally_inverted(int i, int j, char base) {
		int delta_i = i-1;
		int delta_j = j+1;
		
		while (
				(delta_i >= minPosition && delta_j <= maxPosition)
				&& base == dna[delta_i].charAt(delta_j) 
				&& (delta_i > (i-PATTERN_LENGHT) && delta_j < (j+PATTERN_LENGHT))){
			delta_i--;
			delta_j++;
		}
		
		return (delta_i == i-1 && delta_j == j+1)
				|| (delta_i >= minPosition && delta_j<= maxPosition && dna[delta_i].charAt(delta_j) == base);
	}
	
	/**
	 * Busca patrón de cuatro letras seguidas en forma diagonal invertida
	 * @param i
	 * @param j
	 * @param base
	 * @return
	 */
	private boolean matchesDiagonally_inverted(int i, int j, char base) {
		
		int counter = 1;
		int i_offset = i + PATTERN_LENGHT -1;
		int j_offset = j - PATTERN_LENGHT + 1;
		
		while (i_offset <= maxPosition && j_offset >= minPosition && base == dna[i_offset].charAt(j_offset)) {
			
			counter++;
			
			if (counter == PATTERN_LENGHT) {
				return true;
			} 
			
			i_offset--;
			j_offset++;
		}
		
		return false;
	}

}