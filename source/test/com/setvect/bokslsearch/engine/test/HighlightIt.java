package com.setvect.bokslsearch.engine.test;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.util.Version;

public class HighlightIt {
	private static final String text = "북위 49°~54˚, 동경 14°~24°에 걸쳐 중앙유럽의 대평원 지역에 위치하고 있다. "
			+ "(국토의 75%가 해발 200M 이하) 동쪽으로는 벨라루스, 우크라이나, 리투아아 및 러시아(칼리닌그라드 주), 남쪽으로는 체코 및 슬로바키아, "
			+ "서쪽으로는 독일 등 7개국과 접하고 있으며, 북쪽으로는 발트 해에 접한다. 유럽 수도인 바르샤바는 폴란드의 중앙부에 위치하고 있다.";

	public static void main(String[] args) throws IOException, ParseException, InvalidTokenOffsetsException,
			org.apache.lucene.queryParser.ParseException {
		QueryParser queryParser = new QueryParser(Version.LUCENE_35, "", new CJKAnalyzer(Version.LUCENE_35));
		Query q = queryParser.parse("f:유럽");
		QueryScorer scorer = new QueryScorer(q);
		Highlighter highlighter = new Highlighter(scorer);
		Fragmenter fragmenter = new SimpleFragmenter(40);
		highlighter.setTextFragmenter(fragmenter);
		TokenStream tokenStream = new CJKAnalyzer(Version.LUCENE_35).tokenStream("f", new StringReader(text));
		String result = highlighter.getBestFragments(tokenStream, text, 3, "...");
		System.out.println(result);
		
		String[] s = highlighter.getBestFragments(tokenStream, "유럽", 3);
		System.out.println("종료");
	}
}
