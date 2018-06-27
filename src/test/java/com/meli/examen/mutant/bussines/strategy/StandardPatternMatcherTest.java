package com.meli.examen.mutant.bussines.strategy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.meli.examen.mutant.TestHelper;

public class StandardPatternMatcherTest {
	
	private PatternMatcher patternMatcher = new StandardPatternMatcher();
	
	@Test
	public void analizeSequence_human() {
		patternMatcher.setDna(TestHelper.HUMAN_DNA);
		assertFalse(patternMatcher.analizeSequence());
	}
	
	@Test
	public void analizeSequence_humanOneMatchHorizontalCheckPreviousCells() {
		patternMatcher.setDna(TestHelper.HUMAN_DNA_ONE_MATCH_HORIZONTAL_PREVIOUS_CELLS_CHECK);
		assertFalse(patternMatcher.analizeSequence());
	}
	
	@Test
	public void analizeSequence_humanOneMatchHorizontalCheckPreviousCells2() {
		patternMatcher.setDna(TestHelper.HUMAN_DNA_ONE_MATCH_HORIZONTAL_PREVIOUS_CELLS_CHECK_2);
		assertFalse(patternMatcher.analizeSequence());
	}
	
	@Test
	public void analizeSequence_humanOneMatchVerticalCheckPreviousCells() {
		patternMatcher.setDna(TestHelper.HUMAN_DNA_ONE_MATCH_VERTICAL_PREVIOUS_CELLS_CHECK);
		assertFalse(patternMatcher.analizeSequence());
	}
	
	@Test
	public void analizeSequence_humanOneMatchVerticalCheckPreviousCells2() {
		patternMatcher.setDna(TestHelper.HUMAN_DNA_ONE_MATCH_VERTICAL_PREVIOUS_CELLS_CHECK_2);
		assertFalse(patternMatcher.analizeSequence());
	}
	
	@Test
	public void analizeSequence_humanOneMatchDiagonalStraightCheckPreviousCells() {
		patternMatcher.setDna(TestHelper.HUMAN_DNA_ONE_MATCH_DIAGONAL_STRAIGHT_PREVIOUS_CELLS_CHECK);
		assertFalse(patternMatcher.analizeSequence());
	}
	
	@Test
	public void analizeSequence_humanOneMatchDiagonalStraightCheckPreviousCells2() {
		patternMatcher.setDna(TestHelper.HUMAN_DNA_ONE_MATCH_DIAGONAL_STRAIGHT_PREVIOUS_CELLS_CHECK_2);
		assertFalse(patternMatcher.analizeSequence());
	}	
	
	@Test
	public void analizeSequence_humanOneMatchDiagonalInvertedCheckPreviousCells() {
		patternMatcher.setDna(TestHelper.HUMAN_DNA_ONE_MATCH_DIAGONAL_INVERTED_PREVIOUS_CELLS_CHECK);
		assertFalse(patternMatcher.analizeSequence());
	}
	
	@Test
	public void analizeSequence_humanOneMatchDiagonalInvertedCheckPreviousCells2() {
		patternMatcher.setDna(TestHelper.HUMAN_DNA_ONE_MATCH_DIAGONAL_INVERTED_PREVIOUS_CELLS_CHECK_2);
		assertFalse(patternMatcher.analizeSequence());
	}
	
	@Test
	public void analizeSequence_mutant() {
		patternMatcher.setDna(TestHelper.MUTANT_DNA);
		assertTrue(patternMatcher.analizeSequence());
	}
	
	@Test
	public void analizeSequence_mutantMatchHorizontalCheckPreviousCells() {
		patternMatcher.setDna(TestHelper.MUTANT_DNA_TWO_MATCHES_HORIZONTAL_PREVIOUS_CELLS_CHECK);
		assertTrue(patternMatcher.analizeSequence());
	}
	
	@Test
	public void analizeSequence_mutantMatchVerticalCheckPreviousCells() {
		patternMatcher.setDna(TestHelper.MUTANT_DNA_TWO_MATCHES_VERTICAL_PREVIOUS_CELLS_CHECK);
		assertTrue(patternMatcher.analizeSequence());
	}
	
	@Test
	public void analizeSequence_mutantMatchDiagonalStraightCheckPreviousCells() {
		patternMatcher.setDna(TestHelper.MUTANT_DNA_TWO_MATCHES_DIAGONAL_STRAIGHT_PREVIOUS_CELLS_CHECK);
		assertTrue(patternMatcher.analizeSequence());
	}

	@Test
	public void analizeSequence_mutantMatchDiagonalInvertedCheckPreviousCells() {
		patternMatcher.setDna(TestHelper.MUTANT_DNA_TWO_MATCHES_DIAGONAL_INVERTED_PREVIOUS_CELLS_CHECK);
		assertTrue(patternMatcher.analizeSequence());
	}
	
	@Test
	public void analizeSequence_human_bigMatrix() {
		patternMatcher.setDna(TestHelper.getBigHumanMatrix_Triplets());
		assertFalse(patternMatcher.analizeSequence());
	}

}
