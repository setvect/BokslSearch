package com.setvect.bokslsearch.engine.extract;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * TEXT ����
 */
public class XlsExtractor implements FileTextExtractor {
	/**
	 * �ؽ�Ʈ ����
	 * 
	 * @param xlsFile
	 *             ����
	 * @return TEXT
	 */
	public String extract(File xlsFile) {
		InputStream is = null;
		try {
			is = new FileInputStream(xlsFile);
			ExcelExtractor extractor = new ExcelExtractor(new POIFSFileSystem(is));
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
