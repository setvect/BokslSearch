package com.setvect.bokslsearch.engine.extract;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hslf.extractor.PowerPointExtractor;

import com.setvect.common.util.StringUtilAd;

/**
 * TEXT ����
 */
public class PptExtractor implements FileTextExtractor {
	/**
	 * �ؽ�Ʈ ����
	 * 
	 * @param xlsFile
	 *            PDF ����
	 * @return TEXT
	 */
	public String extract(File xlsFile) {
		InputStream is = null;
		try {
			is = new FileInputStream(xlsFile);
			PowerPointExtractor extractor = new PowerPointExtractor(is);
			String text = extractor.getText();
			return StringUtilAd.removeSpecialChar(text.trim());
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
