package com.setvect.bokslsearch.engine.extract;

import java.io.File;

import org.apache.poi.xslf.XSLFSlideShow;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;

import com.setvect.common.util.StringUtilAd;

/**
 * TEXT 추출
 */
public class PptxExtractor implements FileTextExtractor {
	/**
	 * 텍스트 추출
	 * 
	 * @param xlsxFile
	 *             파일
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
