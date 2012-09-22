package com.setvect.bokslsearch.engine.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import com.setvect.bokslsearch.engine.extract.ExtractorEnum;
import com.setvect.bokslsearch.engine.extract.FileTextExtractor;

public class 텍스트추출_TestCase extends TestInit {
	@Test
	public void testPdf() {
		FileTextExtractor extractor = ExtractorEnum.PDF.getExtractor();
		String s = extractor.extract(new File("./doc_sample/sample.pdf"));

		Assert.assertThat(s, is(notNullValue()));
		// System.out.println(s);
	}

	@Test
	public void testXls() {
		FileTextExtractor extractor = ExtractorEnum.XLS.getExtractor();
		String s = extractor.extract(new File("./doc_sample/sample.xls"));
		Assert.assertThat(s, is(notNullValue()));
		// System.out.println(s);
	}

	@Test
	public void testDoc() {
		FileTextExtractor extractor = ExtractorEnum.DOC.getExtractor();
		String s = extractor.extract(new File("./doc_sample/1.doc"));
		Assert.assertThat(s, is(notNullValue()));
		 System.out.println(s);
	}

	@Test
	public void testPpt() {
		FileTextExtractor extractor = ExtractorEnum.PPT.getExtractor();
		String s = extractor.extract(new File("./doc_sample/sample.ppt"));
		Assert.assertThat(s, is(notNullValue()));
		// System.out.println(s);
	}

	@Test
	public void testXlsx() {
		FileTextExtractor extractor = ExtractorEnum.XLSX.getExtractor();
		String s = extractor.extract(new File("./doc_sample/sample.xlsx"));
		Assert.assertThat(s, is(notNullValue()));
		// System.out.println(s);
	}

	@Test
	public void testDocx() {
		FileTextExtractor extractor = ExtractorEnum.DOCX.getExtractor();
		String s = extractor.extract(new File("./doc_sample/sample.docx"));
		Assert.assertThat(s, is(notNullValue()));
		// System.out.println(s);
	}

	@Test
	public void testPptx() {
		FileTextExtractor extractor = ExtractorEnum.PPTX.getExtractor();
		String s = extractor.extract(new File("./doc_sample/sample.pptx"));
		Assert.assertThat(s, is(notNullValue()));
		// System.out.println(s);
	}
}