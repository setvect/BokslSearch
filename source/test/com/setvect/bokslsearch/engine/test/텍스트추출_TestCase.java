package com.setvect.bokslsearch.engine.test;

import java.io.File;

import org.junit.Test;

import com.setvect.bokslsearch.engine.extract.PDFExtractor;

public class �ؽ�Ʈ����_TestCase extends TestInit {
	@Test
	public void testPDF() {
		String s = PDFExtractor.extract(new File("./doc_sample/sample.pdf"));
		System.out.println(s);
		
	}
}