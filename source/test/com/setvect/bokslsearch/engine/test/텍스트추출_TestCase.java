package com.setvect.bokslsearch.engine.test;

import java.io.File;

import org.junit.Test;

import com.setvect.bokslsearch.engine.extract.PDFExtractor;

public class 텍스트추출_TestCase extends TestInit {
	@Test
	public void testPDF() {
		String s = PDFExtractor.extract(new File("./doc_sample/sample.pdf"));
		System.out.println(s);
		
	}
}