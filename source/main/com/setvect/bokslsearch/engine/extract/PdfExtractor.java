package com.setvect.bokslsearch.engine.extract;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.pdfbox.cos.COSDocument;
import org.pdfbox.pdfparser.PDFParser;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

import com.setvect.common.util.StringUtilAd;

/**
 * PDF -> TEXT 추출
 */
public class PdfExtractor implements FileTextExtractor {
	/**
	 * 텍스트 추출
	 * 
	 * @param pdfFile
	 *            PDF 파일
	 * @return TEXT
	 */
	public String extract(File pdfFile) {
		COSDocument cosDoc = null;
		InputStream is = null;
		try {
			is = new FileInputStream(pdfFile);
			cosDoc = parseDocument(is);
			PDFTextStripper striper = new PDFTextStripper();
			String text = striper.getText(new PDDocument(cosDoc));
			return StringUtilAd.removeSpecialChar(text.trim());
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (cosDoc != null) {
				try {
					cosDoc.close();
				} catch (IOException e) {
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
	}

	private static COSDocument parseDocument(InputStream is) throws IOException {
		PDFParser parser = new PDFParser(is);
		parser.parse();
		return parser.getDocument();
	}
}
