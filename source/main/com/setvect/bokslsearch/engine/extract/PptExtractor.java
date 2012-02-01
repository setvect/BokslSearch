package com.setvect.bokslsearch.engine.extract;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hslf.extractor.PowerPointExtractor;

/**
 * TEXT 추출
 */
public class PptExtractor implements FileTextExtractor {
	/**
	 * 텍스트 추출
	 * 
	 * @param xlsFile
	 *            PDF 파일
	 * @return TEXT
	 */
	public String extract(File xlsFile) {
		InputStream is = null;
		try {
			is = new FileInputStream(xlsFile);
			PowerPointExtractor extractor = new PowerPointExtractor(is);
			String text = extractor.getText();
			return text.trim();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
