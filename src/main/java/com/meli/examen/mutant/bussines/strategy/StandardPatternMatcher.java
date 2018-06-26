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
			
			//iteracion horizontal
			while (j <= maxPosition && currentMatches != REQUIRED_MATCHES) {
				
				currentMatches += analizeDirectionally(i,j,currentMatches);
				
				j++;
				
			}
			
			i++;
			
		}
		
		return currentMatches == REQUIRED_MATCHES;
	}

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
	
	private boolean checkPreviousCellsHorizontally(int i, int j, char base) {
		
		int delta_j = j-1;
		
		while (delta_j >= minPosition && base == dna[i].charAt(delta_j) 
				&& delta_j > (j-PATTERN_LENGHT)) {
			delta_j--;
		}

		return delta_j == j-1 || (delta_j >= minPosition && dna[i].charAt(delta_j) == base);
	}
	
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
	
	private boolean checkPreviousCellsVertically(int i, int j, char base) {
		
		int delta_i = i-1;
		
		while (delta_i >= minPosition && base == dna[delta_i].charAt(j) 
				&& delta_i > (i-PATTERN_LENGHT)) {
			delta_i--;
		}
		
		return delta_i == i-1 || (delta_i >= minPosition && dna[delta_i].charAt(j) == base);
	}
	
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