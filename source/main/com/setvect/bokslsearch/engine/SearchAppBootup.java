package com.setvect.bokslsearch.engine;

import java.io.File;
import java.net.URL;

import org.apache.log4j.xml.DOMConfigurator;

import com.setvect.bokslsearch.engine.config.SearchAppProperty;

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
	 * @param log4jInit
	 *            Log4j �ʱ�ȭ(�׽�Ʈ �Ҷ� ���)
	 */
	public static void bootUp(File appHomeDir, boolean log4jInit) {
		if (initialize) {
			return;
			// throw new IllegalStateException("aready initialized!");
		}

		// ���ø����̼� Ȩ ���. Log4J ���� ��ο��� ���
		System.setProperty("APPLICATION_HOME", appHomeDir.getPath());

		File configFile = new File(appHomeDir, CONFIG_CONFIG_PROPERTIES);
		SearchAppProperty.init(configFile);

		if (log4jInit) {
			URL log4j = SearchAppBootup.class.getResource(CONFIG_LOG4J_XML);
			DOMConfigurator.configure(log4j);
			SearchAppLogger.out.info("BokslSearchEngine Log Manager Initialized");
		}
		SearchAppLogger.out.info("BokslSearchEngine Started...");
	}
}
