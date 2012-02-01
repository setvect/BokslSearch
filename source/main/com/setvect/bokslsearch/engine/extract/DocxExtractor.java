package com.setvect.bokslsearch.engine.extract;

import java.io.File;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

/**
 * TEXT ����
 */
public class DocxExtractor implements FileTextExtractor {
	/**
	 * �ؽ�Ʈ ����
	 * 
	 * @param docFile
	 *            ����
	 * @return TEXT
	 */
	public String extract(File docFile) {
		try {
			XWPFWordExtractor wd = new XWPFWordExtractor(POIXMLDocument.openPackage(docFile.getPath()));
			String text = wd.getText();
			return text.trim();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
