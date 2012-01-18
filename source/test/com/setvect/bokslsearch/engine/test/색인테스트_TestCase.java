package com.setvect.bokslsearch.engine.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.setvect.bokslsearch.engine.ApplicationBootup;
import com.setvect.bokslsearch.engine.index.DocRecord;
import com.setvect.bokslsearch.engine.index.DocRecord.DocField;
import com.setvect.bokslsearch.engine.index.IndexService;

public class 색인테스트_TestCase extends TestInit {

	@Test
	public void 문자열색인() throws IOException {
		ClassPathXmlApplicationContext sc = ApplicationBootup.getConfigSpring();
		IndexService indexService = (IndexService) sc.getBean("IndexService");

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
		indexService.indexDocument("test", data);
	}

	@After 
	public void 색인삭제() {
		ClassPathXmlApplicationContext sc = ApplicationBootup.getConfigSpring();
		IndexService indexService = (IndexService) sc.getBean("IndexService");
		indexService.deleteIndex("test");
	}
}