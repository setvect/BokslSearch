package com.setvect.bokslsearch.engine.config;

import java.io.File;

/**
 * ���α׷����� ���Ǵ� ��� ������ ���� <br>
 * �Ӽ��� ��û ���� ConfigProperty Ŭ������ �ʱ�ȭ �Ǿ�ߵ� <br>
 * 
 * @version $Id$
 */
public class ApplicationConstant {
	/** ���α׷� Ȩ ���丮 */
	public static final String APP_HOME_DIR = System.getProperty("APPLICATION_HOME");

	/** ���� ���丮 ��� */
	public static final File INDEX_DIR;
	static {
		String t = ConfigProperty.getString("com.setvect.bokslsearch.engine.index_dir");
		if (t.startsWith("/")) {
			INDEX_DIR = new File(t);
		}
		else {
			INDEX_DIR = new File(APP_HOME_DIR, t);
		}
	}
}
