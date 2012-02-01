package com.setvect.bokslsearch.engine.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import com.setvect.bokslsearch.engine.extract.DocExtractor;
import com.setvect.bokslsearch.engine.extract.DocxExtractor;
import com.setvect.bokslsearch.engine.extract.FileTextExtractor;
import com.setvect.bokslsearch.engine.extract.PdfExtractor;
import com.setvect.bokslsearch.engine.extract.PptExtractor;
import com.setvect.bokslsearch.engine.extract.PptxExtractor;
import com.setvect.bokslsearch.engine.extract.XlsExtractor;
import com.setvect.bokslsearch.engine.extract.XlsxExtractor;

public class 텍스트추출_TestCase extends TestInit {
	@Test
	public void testPdf() {
		FileTextExtractor extractor = new PdfExtractor();
		String s = extractor.extract(new File("./doc_sample/sample.pdf"));

		Assert.assertThat(s, is(notNullValue()));
		// System.out.println(s);
	}

	@Test
	public void testXls() {
		FileTextExtractor extractor = new XlsExtractor();
		String s = extractor.extract(new File("./doc_sample/sample.xls"));
		Assert.assertThat(s, is(notNullValue()));
		// System.out.println(s);
	}

	@Test
	public void testDoc() {
		FileTextExtractor extractor = new DocExtractor();
		String s = extractor.extract(new File("./doc_sample/sample.doc"));
		Assert.assertThat(s, is(notNullValue()));
		// System.out.println(s);
	}

	@Test
	public void testPpt() {
		FileTextExtractor extractor = new PptExtractor();
		String s = extractor.extract(new File("./doc_sample/sample.ppt"));
		Assert.assertThat(s, is(notNullValue()));
		// System.out.println(s);
	}

	@Test
	public void testXlsx() {
		FileTextExtractor extractor = new XlsxExtractor();
		String s = extractor.extract(new File("./doc_sample/sample.xlsx"));
		Assert.assertThat(s, is(notNullValue()));
		// System.out.println(s);
	}

	@Test
	public void testDocx() {
		FileTextExtractor extractor = new DocxExtractor();
		String s = extractor.extract(new File("./doc_sample/sample.docx"));
		Assert.assertThat(s, is(notNullValue()));
		System.out.println(s);
	}

	@Test
	public void testPptx() {
		FileTextExtractor extractor = new PptxExtractor();
		String s = extractor.extract(new File("./doc_sample/sample.pptx"));
		Assert.assertThat(s, is(notNullValue()));
		// System.out.println(s);
	}
}