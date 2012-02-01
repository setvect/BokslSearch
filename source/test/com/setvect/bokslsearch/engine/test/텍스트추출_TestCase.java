package com.setvect.bokslsearch.engine.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import com.setvect.bokslsearch.engine.extract.DocExtractor;
import com.setvect.bokslsearch.engine.extract.DocxExtractor;
import com.setvect.bokslsearch.engine.extract.ExtractorEnum;
import com.setvect.bokslsearch.engine.extract.FileTextExtractor;
import com.setvect.bokslsearch.engine.extract.PdfExtractor;
import com.setvect.bokslsearch.engine.extract.PptExtractor;
import com.setvect.bokslsearch.engine.extract.PptxExtractor;
import com.setvect.bokslsearch.engine.extract.XlsExtractor;
import com.setvect.bokslsearch.engine.extract.XlsxExtractor;

public class �ؽ�Ʈ����_TestCase extends TestInit {
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
		String s = extractor.extract(new File("./doc_sample/sample.doc"));
		Assert.assertThat(s, is(notNullValue()));
		// System.out.println(s);
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