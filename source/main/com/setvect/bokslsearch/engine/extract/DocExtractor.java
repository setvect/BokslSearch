package com.setvect.bokslsearch.engine.extract;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hwpf.extractor.WordExtractor;

import com.setvect.common.util.StringUtilAd;

/**
 * TEXT ����
 */
public class DocExtractor implements FileTextExtractor {
	/**
	 * �ؽ�Ʈ ����
	 * 
	 * @param docFile
	 *            ����
	 * @return TEXT
	 */
	public String extract(File docFile) {
		InputStream is = null;
		try {
			is = new FileInputStream(docFile);
			WordExtractor wd = new WordExtractor(is);
			String text = wd.getText();
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
