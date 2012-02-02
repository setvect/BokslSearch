package com.setvect.bokslsearch.engine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.setvect.bokslsearch.engine.config.SearchAppConstant;

/**
 * ���α׷��� ������ ��ƿ�� �޼ҵ� ����
 */
public class SearchAppUtil {

	/**
	 * ���� ���丮 ���
	 * 
	 * @param indexName
	 *            ���� �̸�
	 * @return ���� ���丮 ���
	 */
	public static File getIndexDir(String indexName) {
		File indexFile = new File(SearchAppConstant.INDEX_DIR, indexName);
		return indexFile;
	}

	/**
	 * �޸��� ��ū�� �и� �Ͽ� ����
	 * 
	 * @param value
	 *            �޸��� �и��� ���ڿ�
	 * @return ����Ʈ
	 */
	public static List<String> toList(String value) {
		List<String> result = new ArrayList<String>();
		CollectionUtils.addAll(result, value.split(","));
		return result;
	}
}
