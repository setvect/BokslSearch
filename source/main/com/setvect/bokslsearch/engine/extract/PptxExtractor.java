package com.setvect.bokslsearch.engine.extract;

import java.io.File;

import org.apache.poi.xslf.XSLFSlideShow;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;

import com.setvect.common.util.StringUtilAd;

/**
 * TEXT ����
 */
public class PptxExtractor implements FileTextExtractor {
	/**
	 * �ؽ�Ʈ ����
	 * 
	 * @param xlsxFile
	 *             ����
	 * @return TEXT
	 */
	public String extract(File xlsxFile) {
		try {
			XSLFPowerPointExtractor extractor = new XSLFPowerPointExtractor(new XSLFSlideShow(xlsxFile.getPath()));
			String text = extractor.getText();
			 return StringUtilAd.removeSpecialChar(text.trim());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
