package com.setvect.bokslsearch.engine.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.KeywordAnalyzer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

/**
 * 형분석 종류
 */
public enum AnalyzerType {
	STANDARD {
		@Override
		public Analyzer getAnalyzer() {
			return new StandardAnalyzer(Version.LUCENE_35);
		}
	},
	CJK {
		@Override
		public Analyzer getAnalyzer() {
			return new CJKAnalyzer(Version.LUCENE_35);
		}
	},
	KEYWORD {
		@Override
		public Analyzer getAnalyzer() {
			return new KeywordAnalyzer();
		}
	},
	;

	public abstract Analyzer getAnalyzer();
}