package com.setvect.bokslsearch.engine;

import java.io.File;
import java.net.URL;

import org.apache.log4j.xml.DOMConfigurator;

import com.setvect.bokslsearch.engine.config.SearchAppProperty;

/**
 * 어플리케이션에 기본적인 설정값, 로그설정등을 해준다. <br>
 */
public class SearchAppBootup {
	private static final String CONFIG_LOG4J_XML = "/com/setvect/bokslsearch/engine/config/log4j.xml";
	private static final String CONFIG_CONFIG_PROPERTIES = "/conf/search.config.properties";

	/** 초기화 여부 */
	private static boolean initialize = false;

	public SearchAppBootup() {
	}

	/**
	 * com.setvect.bokslsearch.engine.config propertity, log4j, spring,
	 * hibernate 설정 초기화
	 * 
	 * @param appHomeDir
	 *            프로그램 홈 디렉토리
	 * @param log4jInit
	 *            Log4j 초기화(테스트 할때 사용)
	 */
	public static void bootUp(File appHomeDir, boolean log4jInit) {
		if (initialize) {
			return;
			// throw new IllegalStateException("aready initialized!");
		}

		// 어플리케이션 홈 경로. Log4J 저장 경로에서 사용
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
