package com.setvect.bokslsearch.engine.analyzer;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;

public class BigramFilter extends TokenFilter {
	protected BigramFilter(TokenStream input) {
		super(input);
	}

	@Override
	public boolean incrementToken() throws IOException {
		return true;
	}
}
