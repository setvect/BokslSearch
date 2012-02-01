package com.setvect.bokslsearch.engine.extract;

import java.io.File;

/**
 * 첨부파일에 텍스트 추출
 */
public interface FileTextExtractor {
	/**
	 * 텍스트 추출
	 * 
	 * @param file
	 *            문서 파일
	 * @return TEXT
	 */
	public String extract(File file);
}
