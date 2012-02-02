package com.setvect.bokslsearch.engine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.setvect.bokslsearch.engine.config.SearchAppConstant;

/**
 * 프로그램에 의존된 유틸성 메소드 제공
 */
public class SearchAppUtil {

	/**
	 * 색인 디렉토리 경로
	 * 
	 * @param indexName
	 *            색인 이름
	 * @return 색인 디렉토리 경로
	 */
	public static File getIndexDir(String indexName) {
		File indexFile = new File(SearchAppConstant.INDEX_DIR, indexName);
		return indexFile;
	}

	/**
	 * 콤마로 토큰을 분리 하여 전달
	 * 
	 * @param value
	 *            콤마로 분리된 문자열
	 * @return 리스트
	 */
	public static List<String> toList(String value) {
		List<String> result = new ArrayList<String>();
		CollectionUtils.addAll(result, value.split(","));
		return result;
	}
}
