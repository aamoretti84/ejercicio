package com.meli.examen.mutant;

import java.util.ArrayList;
import java.util.List;

public class TestHelper {
	
	public static final String[] EMPTY_DNA = new String[0];
	
	public static final String[] NOT_NXN_DNA = {"ATCG","ATCG","ATCG"};
	
	public static final String[] INVALID_CHAR_DNA = {"ATCG","ATCG","ATCG"};
	
	public static final String[] HUMAN_DNA = {"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"};
	
	public static final String[] MUTANT_DNA = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

	public static final String[] HUMAN_DNA_ONE_MATCH_HORIZONTAL_PREVIOUS_CELLS_CHECK = {"AAAAAAAT","GAGTGCCC","CTATTTCC","FGACGGTT","CCGTCAAA","ACACTGTT","AGACGGTT","AAGTGCCC"};

	public static final String[] HUMAN_DNA_ONE_MATCH_HORIZONTAL_PREVIOUS_CELLS_CHECK_2 = {"TAAAAAAA","GAGTGCCC","CTATTTCC","FGACGGTT","CCGTCAAA","ACACTGTT","AGACGGTT","AAGTGCCC"};
	
	public static final String[] HUMAN_DNA_ONE_MATCH_VERTICAL_PREVIOUS_CELLS_CHECK = {"ACGATAAA","AAGTGCCC","ATATTTCC","AGACGGTT","ACGTCAAA","ACACTGTT","AGACGGTT","CAGTGCCC"};
	
	public static final String[] HUMAN_DNA_ONE_MATCH_VERTICAL_PREVIOUS_CELLS_CHECK_2 = {"CCGATAAA","AAGTGCCC","ATATTTCC","AGACGGTT","ACGTCAAA","ACACTGTT","AGACGGTT","AAGTGCCC"};

	public static final String[] HUMAN_DNA_ONE_MATCH_DIAGONAL_STRAIGHT_PREVIOUS_CELLS_CHECK = {"ACACACAC","GAGTGCCC","CTATTTCC","FGAAGGTT","CCGTATAA","ACACTATT","AGACGGAT","AAGTGCCC"};
	
	public static final String[] HUMAN_DNA_ONE_MATCH_DIAGONAL_STRAIGHT_PREVIOUS_CELLS_CHECK_2 = {"CCACACAC","GAGTGCCC","CTATTTCC","FGAAGGTT","CCGTATAA","ACACTATT","AGACGGAT","AAGTGCCA"};
	
	public static final String[] HUMAN_DNA_ONE_MATCH_DIAGONAL_INVERTED_PREVIOUS_CELLS_CHECK = {"ACACACAC","GTGTGCCC","CTATTCCC","FGAGCGTT","CCGCATAA","ACCCTATT","ACACGGAT","AAGTGCCC"};
	
	public static final String[] MUTANT_DNA_TWO_MATCHES_HORIZONTAL_PREVIOUS_CELLS_CHECK = {"AAAAAAAA","GAGTGCCC","CTATTTCC","FGACGGTT","CCGTCAAA","ACACTGTT","AGACGGTT","AAGTGCCC"};
	
	public static final String[] MUTANT_DNA_TWO_MATCHES_VERTICAL_PREVIOUS_CELLS_CHECK = {"ACGATAAA","AAGTGCCC","ATATTTCC","AGACGGTT","ACGTCAAA","ACACTGTT","AGACGGTT","AAGTGCCC"};
	
	public static final String[] MUTANT_DNA_TWO_MATCHES_DIAGONAL_STRAIGHT_PREVIOUS_CELLS_CHECK = {"ACACACAC","GAGTGCCC","CTATTTCC","FGAAGGTT","CCGTATAA","ACACTATT","AGACGGAT","AAGTGCCA"};
	
	public static final String[] MUTANT_DNA_TWO_MATCHES_DIAGONAL_INVERTED_PREVIOUS_CELLS_CHECK = {"ACACACAC","GTGTGCCC","CTATTCCC","FGAGCGTT","CCGCATAA","ACCCTATT","ACACGGAT","CAGTGCCC"};

	public static final String[] HUMAN_DNA_ONE_MATCH_DIAGONAL_INVERTED_PREVIOUS_CELLS_CHECK_2 = {"ACACACAA","GTGTGCCC","CTATTCCC","FGAGCGTT","CCGCATAA","ACCCTATT","ACACGGAT","CAGTGCCC"};
	
	public static String[] getBigHumanMatrix_Triplets() {
		int length = 1200;
		String noMatchingPattern1 = getNoMatchingPattern("AAACCCGGGTTT", length);
		String noMatchingPattern2 = getNoMatchingPattern("CCCGGGTTTAAA", length);
		String noMatchingPattern3 = getNoMatchingPattern("GGGTTTAAACCC", length);
		String noMatchingPattern4 = getNoMatchingPattern("TTTAAACCCGGG", length);

		List<String> list = new ArrayList<String>();
		
		for (int i=0; i<(length/4); i++) {
			list.add(noMatchingPattern1);
			list.add(noMatchingPattern2);
			list.add(noMatchingPattern3);
			list.add(noMatchingPattern4);
		}
		
		return list.toArray(new String[list.size()]);
	}
	
	private static String getNoMatchingPattern(String pattern, int length) {
		StringBuffer sb = new StringBuffer();
		
		for (int j=0; j<(length/12); j++) {
			sb.append(pattern);
		}
		
		return sb.toString();
	}
	
}
