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

public class �����׽�Ʈ_TestCase extends TestInit {

	@Test
	public void ���ڿ�����() throws IOException {
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
		indexService.indexDocument("test", data);
	}

	@After 
	public void ���λ���() {
		ClassPathXmlApplicationContext sc = ApplicationBootup.getConfigSpring();
		IndexService indexService = (IndexService) sc.getBean("IndexService");
		indexService.deleteIndex("test");
	}
}