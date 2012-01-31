package com.setvect.bokslsearch.engine.analyzer;

import java.io.Reader;

import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.StopwordAnalyzerBase;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.cjk.CJKTokenizer;
import org.apache.lucene.util.Version;

/**
 * ���ڿ��� Bigram���� �и� ��Ŵ
 */
public class BigramAnalyzer extends StopwordAnalyzerBase {

	public BigramAnalyzer(Version version) {
		super(version);
	}

	@Override
	protected TokenStreamComponents createComponents(String paramString, Reader paramReader) {
		Tokenizer source = new CJKTokenizer(paramReader);
		return new TokenStreamComponents(source, new StopFilter(this.matchVersion, source, this.stopwords));
	}
}