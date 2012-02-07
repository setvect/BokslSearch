package com.setvect.bokslsearch.engine.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.search.SortField;
import org.apache.lucene.search.BooleanClause.Occur;

import com.setvect.bokslsearch.engine.index.AnalyzerType;

/**
 * 검색 질의<br>
 * 정렬 필드는 하나만 선택할 수 있음<br>
 * 정렬 조건이 없는 경우 정확도 순으로 정렬
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
	private List<QueryPart> queries = new ArrayList<QueryPart>();

	/** 정렬 조건 */
	private SortField sortField;

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
	 * 검색 결과를 가져올 위치. 0 부터 시작
	 * 
	 * @return 검색 결과를 가져올 위치. 0 부터 시작
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
	public List<QueryPart> getQueries() {
		return queries;
	}

	/**
	 * 질의 조건 등록
	 * 
	 * @param field
	 *            필드 이름
	 * @param value
	 *            검색 값
	 * @param analyzer
	 *            분석기
	 */
	public void addQuery(String field, String value, AnalyzerType analyzer) {
		addQuery(value, value, analyzer, Occur.MUST);
	}

	/**
	 * 질의 조건 등록
	 * 
	 * @param field
	 *            필드 이름
	 * @param value
	 *            검색 값
	 * @param analyzer
	 *            분석기
	 * @param occur
	 *            결합 조건
	 */
	public void addQuery(String field, String value, AnalyzerType analyzer, Occur occur) {
		queries.add(new QueryPart(field, value, analyzer, occur));
	}

	/**
	 * 질의 조건 등록
	 * 
	 * @param query
	 *            질의
	 */
	public void addQuery(QueryPart query) {
		queries.add(query);
	}

	/**
	 * 정렬 조건
	 * 
	 * @param fieldName
	 *            정렬 필드
	 * @param asc
	 *            true: 오름차순 정렬, false: 내림차순 정렬
	 */
	public void setSortField(String field, boolean asc) {
		sortField = new SortField(field, SortField.STRING, asc);
	}

	/**
	 * 정렬 조건
	 * 
	 * @param sortField
	 *            정렬 조건
	 */
	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	/**
	 * 정렬 조건
	 * 
	 * @return 정렬 조건
	 */
	public SortField getSortField() {
		return sortField;
	}

}
