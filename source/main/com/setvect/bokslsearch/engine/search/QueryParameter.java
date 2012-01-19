package com.setvect.bokslsearch.engine.search;

import java.util.List;

/**
 * 검색 질의<br>
 */
public class QueryParameter {
	/** 검색 대상 색인 */
	private List<String> index;

	/** 조회 필드 */
	private List<String> returnFields;

	/** 검색 결과를 가져올 위치. 0 부터 시작 */
	private int startPosition;

	/** 결과 가져올 갯수 */
	private int returnCount;

	/** 질의 조건 */
	private String query;

	/**
	 * 검색 대상 색인
	 * 
	 * @return 검색 대상 색인
	 */
	public List<String> getIndex() {
		return index;
	}

	/**
	 * 검색 대상 색인
	 * 
	 * @param index
	 *            검색 대상 색인
	 */
	public void setIndex(List<String> index) {
		this.index = index;
	}

	/**
	 * 검색 결과를 가져올 위치. 0 부터 시작
	 * 
	 * @return 검색 결과를 가져올 위치. 0 부터 시작
	 */
	public List<String> getReturnFields() {
		return returnFields;
	}

	/**
	 * 검색 결과를 가져올 위치. 0 부터 시작
	 * 
	 * @param returnFields
	 *            검색 결과를 가져올 위치. 0 부터 시작
	 */
	public void setReturnFields(List<String> returnFields) {
		this.returnFields = returnFields;
	}

	/**
	 * 결과 가져올 갯수<br>
	 * 결과를 가져올 범위는 startPosition ~ startPosition + returnCount - 1
	 * 
	 * @param startPosition
	 *            결과 가져올 갯수
	 * @param returnCount
	 *            결과 가져올 갯수
	 */
	public void setReturnRange(int startPosition, int returnCount) {
		this.startPosition = startPosition;
		this.returnCount = returnCount;
	}

	/**
	 * 결과 가져올 갯수
	 * 
	 * @return 결과 가져올 갯수
	 */
	public int getStartPosition() {
		return startPosition;
	}

	/**
	 * 결과 가져올 갯수
	 * 
	 * @return 결과 가져올 갯수
	 */
	public int getReturnCount() {
		return returnCount;
	}

	/**
	 * 질의 조건
	 * 
	 * @return 질의 조건
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * 질의 조건
	 * 
	 * @param query
	 *            질의 조건
	 */
	public void setQuery(String query) {
		this.query = query;
	}
}
