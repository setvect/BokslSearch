package com.setvect.bokslsearch.engine.extract;

import java.io.File;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import com.setvect.common.util.StringUtilAd;

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
			return StringUtilAd.removeSpecialChar(text.trim());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
