package com.setvect.bokslsearch.engine.test;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.Version;
import org.junit.Test;

public class 형분석_TestCase extends TestInit {
	@Test
	public void test() throws IOException {
		String text = "안녕하세요. 여러분, 방갑습니다. Hello, Has me doing";
		// Analyzer analyzer = new WhitespaceAnalyzer(Version.LUCENE_35);
		Analyzer analyzer = new CJKAnalyzer(Version.LUCENE_35);
		TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(text));

		CharTermAttribute termAtt = tokenStream.getAttribute(CharTermAttribute.class);
		PositionIncrementAttribute posIncrAtt = tokenStream.addAttribute(PositionIncrementAttribute.class);
		OffsetAttribute offsetAtt = tokenStream.addAttribute(OffsetAttribute.class);

		while (tokenStream.incrementToken()) {
			String word = termAtt.toString();
			int postIncrAttr = posIncrAtt.getPositionIncrement();
			int startOffSet = offsetAtt.startOffset();
			int endOffSet = offsetAtt.endOffset();
			System.out.println("\t" + word + " [" + postIncrAttr + "][" + startOffSet + "][" + endOffSet + "]");
		}
	}
}