package com.setvect.bokslsearch.engine.extract;

import java.io.File;

/**
 * ÷�����Ͽ� �ؽ�Ʈ ����
 */
public interface FileTextExtractor {
	/**
	 * �ؽ�Ʈ ����
	 * 
	 * @param file
	 *            ���� ����
	 * @return TEXT
	 */
	public String extract(File file);
}
