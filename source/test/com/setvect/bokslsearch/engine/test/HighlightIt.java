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
	private static final String text = "���� 49��~54��, ���� 14��~24�ƿ� ���� �߾������� ����� ������ ��ġ�ϰ� �ִ�. "
			+ "(������ 75%�� �ع� 200M ����) �������δ� ����罺, ��ũ���̳�, �����ƾ� �� ���þ�(Į���ѱ׶�� ��), �������δ� ü�� �� ���ι�Ű��, "
			+ "�������δ� ���� �� 7������ ���ϰ� ������, �������δ� ��Ʈ �ؿ� ���Ѵ�. ���� ������ �ٸ����ٴ� �������� �߾Ӻο� ��ġ�ϰ� �ִ�.";

	public static void main(String[] args) throws IOException, ParseException, InvalidTokenOffsetsException,
			org.apache.lucene.queryParser.ParseException {
		QueryParser queryParser = new QueryParser(Version.LUCENE_35, "", new CJKAnalyzer(Version.LUCENE_35));
		Query q = queryParser.parse("f:����");
		QueryScorer scorer = new QueryScorer(q);
		Highlighter highlighter = new Highlighter(scorer);
		Fragmenter fragmenter = new SimpleFragmenter(40);
		highlighter.setTextFragmenter(fragmenter);
		TokenStream tokenStream = new CJKAnalyzer(Version.LUCENE_35).tokenStream("f", new StringReader(text));
		String result = highlighter.getBestFragments(tokenStream, text, 3, "...");
		
		System.out.println(result);
		System.out.println("����");
	}
}
