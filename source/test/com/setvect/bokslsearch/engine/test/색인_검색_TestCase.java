package com.setvect.bokslsearch.engine.test;

import static org.hamcrest.CoreMatchers.is;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanClause.Occur;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.setvect.bokslsearch.engine.SearchAppUtil;
import com.setvect.bokslsearch.engine.config.SearchAppConstant;
import com.setvect.bokslsearch.engine.index.AnalyzerType;
import com.setvect.bokslsearch.engine.index.IndexMetadata;
import com.setvect.bokslsearch.engine.index.IndexService;
import com.setvect.bokslsearch.engine.search.QueryParameter;
import com.setvect.bokslsearch.engine.search.QueryPart;
import com.setvect.bokslsearch.engine.search.SearchService;
import com.setvect.bokslsearch.engine.vo.DocRecord;
import com.setvect.bokslsearch.engine.vo.DocRecord.DocField;
import com.setvect.bokslsearch.engine.vo.SearchResult;

public class 색인_검색_TestCase extends TestInit {

	private static final String INDEX_NAME = "test";

	private static final String INDEX_NAME2 = "test2";

	@BeforeClass
	public static void 문자열색인() throws IOException {

		List<DocRecord> data = new ArrayList<DocRecord>();
		DocRecord r1 = new DocRecord();
		DocField field1 = new DocField();
		field1.setName("TITLE");
		field1.setValue("Boys be ambitious");
		field1.setAnalyzerType(AnalyzerType.CJK);
		r1.addField(field1);
		DocField field2 = new DocField();
		field2.setName("CONTENT");
		field2.setValue("울라라라 메렁");
		field2.setAnalyzerType(AnalyzerType.CJK);
		r1.addField(field2);
		data.add(r1);

		DocRecord r2 = new DocRecord();
		field1 = new DocField();
		field1.setName("TITLE");
		field1.setValue("애국가");
		field1.setAnalyzerType(AnalyzerType.CJK);
		r2.addField(field1);
		field2 = new DocField();
		field2.setName("CONTENT");
		field2.setValue("동해물과 백두산이 마르고 닳토록 하느님이 보우하사 우리나라 만세");
		field2.setAnalyzerType(AnalyzerType.CJK);
		r2.addField(field2);
		data.add(r2);

		DocRecord r3 = new DocRecord();
		field1 = new DocField();
		field1.setName("TITLE");
		field1.setValue("Java Programing");
		field1.setAnalyzerType(AnalyzerType.CJK);
		r3.addField(field1);
		field2 = new DocField();
		field2.setName("CONTENT");
		field2.setValue("Hello Java World!");
		field2.setAnalyzerType(AnalyzerType.CJK);
		r3.addField(field2);
		data.add(r3);

		IndexService.indexDocument(INDEX_NAME, data);
		IndexService.indexDocument(INDEX_NAME2, data);
	}

	@Test
	public void 메타정보_조회() throws IOException {
		IndexMetadata result = IndexService.getIndexInfo(INDEX_NAME);
		System.out.println(result.toString());
		Assert.assertThat(result.getDeleteCount(), is(0));
		Assert.assertThat(result.getDocCount(), is(3));
		Assert.assertThat(result.getFieldName().size(), is(2));
	}

	@Test
	public void 검색() throws ParseException, IOException {
		QueryParameter query = new QueryParameter();
		query.setIndex(SearchAppUtil.toList(INDEX_NAME + "," + INDEX_NAME2));
		query.addQuery("CONTENT", "울라 OR 메렁", AnalyzerType.CJK, Occur.MUST);
		query.setReturnRange(0, 5);
		query.setReturnFields(SearchAppUtil.toList("TITLE,CONTENT"));

		SearchResult result = SearchService.search(query);
		Assert.assertThat(result.getTotalHits(), is(2));
		Assert.assertThat(result.getCurrentHits(), is(2));
		Assert.assertThat(result.getValue(0, "TITLE"), is("Boys be ambitious"));

		query.setReturnRange(0, 1);
		result = SearchService.search(query);
		Assert.assertThat(result.getTotalHits(), is(2));
		Assert.assertThat(result.getCurrentHits(), is(1));
		Assert.assertThat(result.getValue(0, "TITLE"), is("Boys be ambitious"));
	}

	@Test
	public void 복잡검색() throws IOException, ParseException {
		QueryParameter query = new QueryParameter();
		query.setIndex(SearchAppUtil.toList(INDEX_NAME + "," + INDEX_NAME2));
		query.addQuery("CONTENT", "울라 OR 메렁", AnalyzerType.CJK, Occur.SHOULD);
		query.addQuery("CONTENT", "동해", AnalyzerType.CJK, Occur.SHOULD);
		query.setReturnRange(0, 5);
		query.setReturnFields(SearchAppUtil.toList("TITLE,CONTENT"));

		SearchResult result = SearchService.search(query);
		Assert.assertThat(result.getTotalHits(), is(4));

		// -------
		query = new QueryParameter();
		query.setIndex(SearchAppUtil.toList(INDEX_NAME + "," + INDEX_NAME2));
		query.addQuery("CONTENT", "울라 OR 메렁", AnalyzerType.CJK, Occur.MUST);
		query.addQuery("CONTENT", "동해", AnalyzerType.CJK, Occur.SHOULD);
		query.setReturnRange(0, 5);
		query.setReturnFields(SearchAppUtil.toList("TITLE,CONTENT"));

		result = SearchService.search(query);
		Assert.assertThat(result.getTotalHits(), is(2));

		// -------
		query = new QueryParameter();
		query.setIndex(SearchAppUtil.toList(INDEX_NAME + "," + INDEX_NAME2));
		query.addQuery("CONTENT", "울라 OR 메렁", AnalyzerType.CJK, Occur.MUST);
		query.addQuery("CONTENT", "동해", AnalyzerType.CJK, Occur.MUST_NOT);
		query.setReturnRange(0, 5);
		query.setReturnFields(SearchAppUtil.toList("TITLE,CONTENT"));

		result = SearchService.search(query);
		Assert.assertThat(result.getTotalHits(), is(2));

		// -------
		query = new QueryParameter();
		query.setIndex(SearchAppUtil.toList(INDEX_NAME + "," + INDEX_NAME2));
		query.addQuery("", "(CONTENT:울라) OR (TITLE:Java)", AnalyzerType.CJK, Occur.MUST);
		query.setReturnRange(0, 5);
		query.setReturnFields(SearchAppUtil.toList("TITLE,CONTENT"));

		result = SearchService.search(query);
		Assert.assertThat(result.getTotalHits(), is(4));

		// printSearchResult(result);
	}

	@Test
	public void 정렬() throws IOException, ParseException {
		QueryParameter query = new QueryParameter();
		query.setIndex(SearchAppUtil.toList(INDEX_NAME + "," + INDEX_NAME2));
		query.addQuery("", "(CONTENT:울라) OR (TITLE:Java)", AnalyzerType.CJK, Occur.MUST);
		query.setReturnRange(0, 5);
		query.setReturnFields(SearchAppUtil.toList("TITLE,CONTENT"));
		query.setSortField("TITLE", true);
		SearchResult result = SearchService.search(query);

		printSearchResult(result);

		query = new QueryParameter();
		query.setIndex(SearchAppUtil.toList(INDEX_NAME + "," + INDEX_NAME2));
		query.addQuery("", "(CONTENT:울라) OR (TITLE:Java)", AnalyzerType.CJK, Occur.MUST);
		query.setReturnRange(0, 5);
		query.setReturnFields(SearchAppUtil.toList("TITLE,CONTENT"));
		query.setSortField("TITLE", false);
		result = SearchService.search(query);
		printSearchResult(result);
	}

	@Test
	public void 문서삭제() throws IOException, ParseException {
		QueryPart query = new QueryPart("CONTENT", "울라 OR 메렁", AnalyzerType.CJK, Occur.SHOULD);
		IndexService.deleteDocument(INDEX_NAME, query);

		IndexMetadata result = IndexService.getIndexInfo(INDEX_NAME);
		System.out.println(result.toString());
		Assert.assertThat(result.getDeleteCount(), is(1));
		Assert.assertThat(result.getDocCount(), is(2));
		Assert.assertThat(result.getFieldName().size(), is(2));
	}

	private void printSearchResult(SearchResult result) {
		System.out.printf("조회 문서수: %,d, 현재 페이지 문서수: %,d\n", result.getTotalHits(), result.getCurrentHits());
		List<Map<String, String>> records = result.getRecords();
		for (Map<String, String> r : records) {
			System.out.printf("%s, %s, %s, %s\n", r.get("TITLE"), r.get("CONTENT"),
					r.get(SearchAppConstant.RESERVED_FIELD.DOC_ID), r.get(SearchAppConstant.RESERVED_FIELD.SCORE));
		}
	}

	@AfterClass
	public static void 색인삭제() {
		IndexService.deleteIndex(INDEX_NAME);
		IndexService.deleteIndex(INDEX_NAME2);
		System.out.println("색인 삭제");
	}
}