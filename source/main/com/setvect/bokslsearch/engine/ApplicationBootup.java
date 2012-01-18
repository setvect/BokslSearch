package com.setvect.bokslsearch.engine;

import java.io.File;
import java.net.URL;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.setvect.bokslsearch.engine.config.ConfigProperty;
import com.setvect.common.log.LogPrinter;

/**
 * 어플리케이션에 기본적인 설정값, 로그설정등을 해준다. <br>
 */
public class ApplicationBootup {
	private static final String CONFIG_SPRING = "classpath:config/applicationContext.xml";
	private static final String CONFIG_LOG4J_XML = "/config/log4j.xml";
	private static final String CONFIG_CONFIG_PROPERTIES = "/conf/config.properties";

	/** 초기화 여부 */
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
	 * config propertity, log4j, spring, hibernate 설정 초기화
	 * 
	 * @param appHomeDir
	 *            프로그램 홈 디렉토리
	 */
	public static void bootUp(File appHomeDir) {
		if (initialize) {
			return;
			// throw new IllegalStateException("aready initialized!");
		}

		// 어플리케이션 홈 경로. Log4J 저장 경로에서 사용
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
