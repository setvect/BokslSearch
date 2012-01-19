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
import com.setvect.bokslsearch.engine.index.IndexMetadata;
import com.setvect.bokslsearch.engine.index.IndexService;
import com.setvect.bokslsearch.engine.search.QueryParameter;
import com.setvect.bokslsearch.engine.search.SearchService;
import com.setvect.bokslsearch.engine.vo.DocRecord;
import com.setvect.bokslsearch.engine.vo.DocRecord.DocField;
import com.setvect.bokslsearch.engine.vo.SearchResult;

public class ����_�˻�_TestCase extends TestInit {

	private static final String INDEX_NAME = "test";

	private static final String INDEX_NAME2 = "test2";

	private IndexService indexService = new IndexService();

	@Before
	public void ���ڿ�����() throws IOException {

		List<DocRecord> data = new ArrayList<DocRecord>();
		DocRecord r1 = new DocRecord();
		DocField field1 = new DocField();
		field1.setName("TITLE");
		field1.setValue("Boys be ambitious");
		r1.addField(field1);
		DocField field2 = new DocField();
		field2.setName("CONTENT");
		field2.setValue("����� �޷�");
		r1.addField(field2);
		data.add(r1);

		DocRecord r2 = new DocRecord();
		field1 = new DocField();
		field1.setName("TITLE");
		field1.setValue("�ֱ���");
		r2.addField(field1);
		field2 = new DocField();
		field2.setName("CONTENT");
		field2.setValue("���ع��� ��λ��� ������ ����� �ϴ����� �����ϻ� �츮���� ����");
		r2.addField(field2);
		data.add(r2);

		DocRecord r3 = new DocRecord();
		field1 = new DocField();
		field1.setName("TITLE");
		field1.setValue("Java Programing");
		r3.addField(field1);
		field2 = new DocField();
		field2.setName("CONTENT");
		field2.setValue("Hello Java World!");
		r3.addField(field2);
		data.add(r3);
		indexService.indexDocument(INDEX_NAME, data);
		indexService.indexDocument(INDEX_NAME2, data);
	}

	@Test
	public void ��Ÿ����_��ȸ() throws IOException {
		IndexMetadata result = indexService.getIndexInfo(INDEX_NAME);
		System.out.println(result.toString());
		Assert.assertThat(result.getDeleteCount(), is(0));
		Assert.assertThat(result.getDocCount(), is(3));
		Assert.assertThat(result.getFieldName().size(), is(2));
	}

	@Test
	public void �˻�() throws ParseException, IOException {
		SearchService searcher = new SearchService();

		QueryParameter query = new QueryParameter();
		query.setIndex(ApplicationUtil.toList(INDEX_NAME + "," + INDEX_NAME2));
		query.setQuery("TITLE:ambitious");
		query.setReturnRange(0, 5);
		query.setReturnFields(ApplicationUtil.toList("TITLE,CONTENT"));

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
	public void ���λ���() {
		indexService.deleteIndex(INDEX_NAME);
		indexService.deleteIndex(INDEX_NAME2);
		System.out.println("���� ����");
	}
}