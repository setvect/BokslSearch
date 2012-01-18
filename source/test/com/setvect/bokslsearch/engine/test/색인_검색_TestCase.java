package com.setvect.bokslsearch.engine.test;

import static org.hamcrest.CoreMatchers.is;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.setvect.bokslsearch.engine.index.DocRecord;
import com.setvect.bokslsearch.engine.index.DocRecord.DocField;
import com.setvect.bokslsearch.engine.index.IndexMetadata;
import com.setvect.bokslsearch.engine.index.IndexService;

public class 색인_검색_TestCase extends TestInit {

	private static final String INDEX_NAME = "test";

	private IndexService indexService = new IndexService();

	@Before
	public void 문자열색인() throws IOException {

		List<DocRecord> data = new ArrayList<DocRecord>();
		DocRecord r1 = new DocRecord();
		DocField field1 = new DocField();
		field1.setName("TITLE");
		field1.setValue("Boys be ambitious");
		r1.addField(field1);
		DocField field2 = new DocField();
		field2.setName("CONTENT");
		field2.setValue("울라라라 메렁");
		r1.addField(field2);
		data.add(r1);

		DocRecord r2 = new DocRecord();
		field1 = new DocField();
		field1.setName("TITLE");
		field1.setValue("애국가");
		r2.addField(field1);
		field2 = new DocField();
		field2.setName("CONTENT");
		field2.setValue("동해물과 백두산이 마르고 닳토록 하느님이 보우하사 우리나라 만세");
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
	}

	@Test
	public void 메타정보_조회() throws IOException {
		IndexMetadata result = indexService.getIndexInfo(INDEX_NAME);
		System.out.println(result.toString());
		Assert.assertThat(result.getDeleteCount(), is(0));
		Assert.assertThat(result.getDocCount(), is(3));
		Assert.assertThat(result.getFieldName().size(), is(2));
	}

	@After
	public void 색인삭제() {
		indexService.deleteIndex(INDEX_NAME);
		System.out.println("색인 삭제");
	}
}