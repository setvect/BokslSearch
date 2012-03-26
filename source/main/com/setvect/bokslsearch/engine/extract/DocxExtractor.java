package com.setvect.bokslsearch.engine.extract;

import java.io.File;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import com.setvect.common.util.StringUtilAd;

/**
 * TEXT 추출
 */
public class DocxExtractor implements FileTextExtractor {
	/**
	 * 텍스트 추출
	 * 
	 * @param docFile
	 *            파일
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
