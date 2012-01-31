package com.setvect.bokslsearch.engine.test;

import static org.hamcrest.CoreMatchers.is;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryParser.ParseException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.setvect.bokslsearch.engine.ApplicationUtil;
import com.setvect.bokslsearch.engine.index.AnalyzerType;
import com.setvect.bokslsearch.engine.index.IndexMetadata;
import com.setvect.bokslsearch.engine.index.IndexService;
import com.setvect.bokslsearch.engine.search.QueryParameter;
import com.setvect.bokslsearch.engine.search.SearchService;
import com.setvect.bokslsearch.engine.vo.DocRecord;
import com.setvect.bokslsearch.engine.vo.DocRecord.DocField;
import com.setvect.bokslsearch.engine.vo.SearchResult;

public class 색인_검색_TestCase extends TestInit {

	private static final String INDEX_NAME = "test";

	private static final String INDEX_NAME2 = "test2";

	private IndexService indexService = new IndexService();

	@Before
	public void 문자열색인() throws IOException {

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
		indexService.indexDocument(INDEX_NAME, data);
		indexService.indexDocument(INDEX_NAME2, data);
	}

	@Test
	public void 메타정보_조회() throws IOException {
		IndexMetadata result = indexService.getIndexInfo(INDEX_NAME);
		System.out.println(result.toString());
		Assert.assertThat(result.getDeleteCount(), is(0));
		Assert.assertThat(result.getDocCount(), is(3));
		Assert.assertThat(result.getFieldName().size(), is(2));

		List<String> a = result.getFieldName();
		for (String c : a) {
			System.out.println(c);
		}

	}

	@Test
	public void 검색() throws ParseException, IOException {
		SearchService searcher = new SearchService();

		QueryParameter query = new QueryParameter();
		query.setIndex(ApplicationUtil.toList(INDEX_NAME + "," + INDEX_NAME2));
		query.setQuery("CONTENT:울라 OR 메렁");
		query.setReturnRange(0, 5);
		query.setReturnFields(ApplicationUtil.toList("TITLE,TITLE_I,CONTENT,CONTENT_I"));

		SearchResult result = searcher.search(query);
		Assert.assertThat(result.getTotalHits(), is(2));
		Assert.assertThat(result.getCurrentHits(), is(2));
		Assert.assertThat(result.getValue(0, "TITLE"), is("Boys be ambitious"));

		query.setReturnRange(0, 1);
		result = searcher.search(query);
		Assert.assertThat(result.getTotalHits(), is(2));
		Assert.assertThat(result.getCurrentHits(), is(1));
		Assert.assertThat(result.getValue(0, "TITLE"), is("Boys be ambitious"));
	}

	@After
	public void 색인삭제() {
		indexService.deleteIndex(INDEX_NAME);
		indexService.deleteIndex(INDEX_NAME2);
		System.out.println("색인 삭제");
	}
}