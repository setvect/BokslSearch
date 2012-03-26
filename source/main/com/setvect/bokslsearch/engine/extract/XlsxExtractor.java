package com.setvect.bokslsearch.engine.extract;

import java.io.File;

import org.apache.poi.xssf.extractor.XSSFExcelExtractor;

import com.setvect.common.util.StringUtilAd;

/**
 * TEXT 추출
 */
public class XlsxExtractor implements FileTextExtractor {
	/**
	 * 텍스트 추출
	 * 
	 * @param xlsxFile
	 *            파일
	 * @return TEXT
	 */
	public String extract(File xlsxFile) {
		try {
			XSSFExcelExtractor extractor = new XSSFExcelExtractor(xlsxFile.getPath());
			String text = extractor.getText();
			text = text.replace("null\t", "");
			text = text.replace("null\n", "");
			text = text.replace("\t", " ");
			return StringUtilAd.removeSpecialChar(text.trim());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
