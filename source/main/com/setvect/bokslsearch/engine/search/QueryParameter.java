package com.setvect.bokslsearch.engine.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.search.BooleanClause.Occur;

import com.setvect.bokslsearch.engine.index.AnalyzerType;
import com.setvect.common.util.StringUtilAd;

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
	private List<QueryPart> queries = new ArrayList<QueryParameter.QueryPart>();

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
	 * 단위 질의 조건<br>
	 * 필드값 입력없이 value에서 조건절이 들어갈 수도 있다.<br>
	 * 왜냐하면 필드값이 들어가면 두개 이상의 필드로 조합되는 조건을 사용하지 못하기 때문이다.
	 */
	public static class QueryPart {
		/** 필드 이름 */
		private final String field;
		/** 검색 값 */
		private final String value;
		/** 형분석 */
		private final AnalyzerType analyzer;
		/** 결합 조건 */
		private final Occur occur;

		/**
		 * @param field
		 *            필드 이름
		 * @param value
		 *            검색 값
		 * @param analyzer
		 *            형분석
		 * @param occur
		 *            결합 조건
		 */
		private QueryPart(String field, String value, AnalyzerType analyzer, Occur occur) {
			this.field = field;
			this.value = value;
			this.analyzer = analyzer;
			this.occur = occur;
		}

		/**
		 * 필드 이름
		 * 
		 * @return the field
		 */
		public String getField() {
			return field;
		}

		/**
		 * 검색 값
		 * 
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/**
		 * 결합 조건
		 * 
		 * @return the occur
		 */
		public Occur getOccur() {
			return occur;
		}

		/**
		 * 형분석
		 * 
		 * @return the analyzer
		 */
		public AnalyzerType getAnalyzer() {
			return analyzer;
		}

		/**
		 * Lucene가 이해하는 조건절
		 * 
		 * @return 조건절
		 */
		public String getClause() {
			if (StringUtilAd.isEmpty(field)) {
				// 필드값 입력없이 value에서 조건절이 들어갈 수도 있다.
				return value;
			}
			return field + ":" + value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "QueryPart [field=" + field + ", value=" + value + ", analyzer=" + analyzer + ", occur=" + occur
					+ "]";
		}
	}
}
