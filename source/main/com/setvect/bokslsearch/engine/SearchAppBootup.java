package com.setvect.bokslsearch.engine;

import java.io.File;
import java.net.URL;

import com.setvect.bokslsearch.engine.config.SearchAppProperty;
import com.setvect.common.log.LogPrinter;

/**
 * ���ø����̼ǿ� �⺻���� ������, �α׼������� ���ش�. <br>
 */
public class SearchAppBootup {
	private static final String CONFIG_LOG4J_XML = "/com/setvect/bokslsearch/engine/config/log4j.xml";
	private static final String CONFIG_CONFIG_PROPERTIES = "/conf/search.config.properties";

	/** �ʱ�ȭ ���� */
	private static boolean initialize = false;

	public SearchAppBootup() {
	}

	/**
	 * com.setvect.bokslsearch.engine.config propertity, log4j, spring,
	 * hibernate ���� �ʱ�ȭ
	 * 
	 * @param appHomeDir
	 *            ���α׷� Ȩ ���丮
	 */
	public static void bootUp(File appHomeDir) {
		if (initialize) {
			return;
			// throw new IllegalStateException("aready initialized!");
		}

		// ���ø����̼� Ȩ ���. Log4J ���� ��ο��� ���
		System.setProperty("APPLICATION_HOME", appHomeDir.getPath());

		File configFile = new File(appHomeDir, CONFIG_CONFIG_PROPERTIES);
		SearchAppProperty.init(configFile);

		URL log4j = SearchAppBootup.class.getResource(CONFIG_LOG4J_XML);
		LogPrinter.init(log4j);
		LogPrinter.out.info("Log Manager Initialized");

		LogPrinter.out.info("Started...");
	}
}
