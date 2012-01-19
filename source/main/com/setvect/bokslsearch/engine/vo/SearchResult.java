package com.setvect.bokslsearch.engine.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 검색결과
 */
public class SearchResult {
	/** 검색된 문서 총 수 */
	private int totalHits;
	/** 조회된 결과 값 */
	private List<Map<String, String>> records = new ArrayList<Map<String, String>>();

	/**
	 * 검색된 문서 총 수
	 * 
	 * @return 검색된 문서 총 수
	 */
	public int getTotalHits() {
		return totalHits;
	}

	/**
	 * 검색된 문서 총 수
	 * 
	 * @param totalHits
	 *            검색된 문서 총 수
	 */
	public void setTotalHits(int docTotalCount) {
		this.totalHits = docTotalCount;
	}

	/**
	 * 현재 페이지에 한하여 검색된 문서 수
	 * 
	 * @return 현재 페이지에 한하여 검색된 문서 수
	 */
	public int getCurrentHits() {
		return records.size();
	}

	/**
	 * 조회된 결과 값
	 * 
	 * @return 조회된 결과 값
	 */
	public List<Map<String, String>> getRecords() {
		return records;
	}

	/**
	 * 조회된 결과 값
	 * 
	 * @param records
	 *            조회된 결과 값
	 */
	public void addRecord(Map<String, String> record) {
		this.records.add(record);
	}

	/**
	 * @param rowNum
	 *            행 번호 0 부터 시작
	 * @param fieldName
	 */
	public String getValue(int rowNum, String fieldName) {
		Map<String, String> map = records.get(rowNum);
		return map.get(fieldName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchResult [totalHits=" + totalHits + ", records=" + records + "]";
	}

}
