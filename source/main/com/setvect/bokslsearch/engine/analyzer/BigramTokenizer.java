package com.setvect.bokslsearch.engine.analyzer;

import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.Tokenizer;

/**
 * 입력되는 문장을 읽어 Token으로 만들어 return split 기준은 스페이스, 특수문자, 한글 / 영문,숫자
 * 
 * @author 장용석, 2011.07.16 need4spd@naver.com
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
