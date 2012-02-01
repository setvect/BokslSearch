package com.setvect.bokslsearch.engine.extract;

import java.io.File;

import org.apache.poi.xssf.extractor.XSSFExcelExtractor;

/**
 * TEXT ����
 */
public class XlsxExtractor implements FileTextExtractor {
	/**
	 * �ؽ�Ʈ ����
	 * 
	 * @param xlsxFile
	 *            ����
	 * @return TEXT
	 */
	public String extract(File xlsxFile) {
		try {
			XSSFExcelExtractor extractor = new XSSFExcelExtractor(xlsxFile.getPath());
			String text = extractor.getText();
			text = text.replace("null\t", "");
			text = text.replace("null\n", "");
			text = text.replace("\t", " ");
			return text.trim();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
