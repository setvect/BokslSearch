package com.setvect.bokslsearch.engine;

import java.io.File;
import java.net.URL;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.setvect.bokslsearch.engine.config.ConfigProperty;
import com.setvect.common.log.LogPrinter;

/**
 * ���ø����̼ǿ� �⺻���� ������, �α׼������� ���ش�. <br>
 */
public class ApplicationBootup {
	private static final String CONFIG_SPRING = "classpath:config/applicationContext.xml";
	private static final String CONFIG_LOG4J_XML = "/config/log4j.xml";
	private static final String CONFIG_CONFIG_PROPERTIES = "/conf/config.properties";

	/** �ʱ�ȭ ���� */
	private static boolean initialize = false;
	private static ClassPathXmlApplicationContext springContext;

	public ApplicationBootup() {
	}

	/**
	 * @return the springContext
	 */
	public static ClassPathXmlApplicationContext getConfigSpring() {
		return springContext;
	}

	/**
	 * config propertity, log4j, spring, hibernate ���� �ʱ�ȭ
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
		ConfigProperty.init(configFile);

		URL log4j = ApplicationBootup.class.getResource(CONFIG_LOG4J_XML);
		LogPrinter.init(log4j);
		LogPrinter.out.info("Log Manager Initialized");

		springContext = new ClassPathXmlApplicationContext(new String[] { CONFIG_SPRING }, false);
		springContext.refresh();

		LogPrinter.out.info("Spring Initialized");

		LogPrinter.out.info("Started...");
	}
}
