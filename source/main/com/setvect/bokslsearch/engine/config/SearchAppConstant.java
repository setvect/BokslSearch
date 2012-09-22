package com.setvect.bokslsearch.engine.config;

import java.io.File;

/**
 * 프로그램에서 사용되는 상수 값들의 모음 <br>
 * 속성값 요청 전에 SearchAppProperty 클래스가 초기화 되어야됨 <br>
 * 
 * @version $Id$
 */
public class SearchAppConstant {
	/** 프로그램 홈 디렉토리 */
	public static final String APP_HOME_DIR = System.getProperty("APPLICATION_HOME");

	/** 색인 디렉토리 경로 */
	public static final File INDEX_DIR;
	static {
		String t = SearchAppProperty.getString("com.setvect.bokslsearch.engine.index_dir");
		if (t.startsWith("/")) {
			INDEX_DIR = new File(t);
		}
		else {
			INDEX_DIR = new File(APP_HOME_DIR, t);
		}
	}

	/**
	 * 검색 결과 예약 필드명
	 */
	public static class RESERVED_FIELD {
		/** 정확도 */
		public static final String SCORE = "_SCORE";

		/** 문서아이디 */
		public static final String DOC_ID = "_DOC_ID";
	}
}
