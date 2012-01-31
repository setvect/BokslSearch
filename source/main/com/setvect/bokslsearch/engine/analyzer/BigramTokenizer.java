package com.setvect.bokslsearch.engine.analyzer;

import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.Tokenizer;

/**
 * �ԷµǴ� ������ �о� Token���� ����� return split ������ �����̽�, Ư������, �ѱ� / ����,����
 * 
 * @author ��뼮, 2011.07.16 need4spd@naver.com
 */

public class BigramTokenizer extends Tokenizer {
	public BigramTokenizer(Reader input) {
		super(input);

	}

	public void reset(Reader input) throws IOException {
		super.reset(input);
	}

	@Override
	public boolean incrementToken() throws IOException {
		clearAttributes();
		return true;
	}
}
