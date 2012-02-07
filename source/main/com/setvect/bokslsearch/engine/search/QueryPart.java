package com.setvect.bokslsearch.engine.search;

import org.apache.lucene.search.BooleanClause.Occur;

import com.setvect.bokslsearch.engine.index.AnalyzerType;
import com.setvect.common.util.StringUtilAd;

/**
 * 단위 질의 조건<br>
 * 필드값 입력없이 value에서 조건절이 들어갈 수도 있다.<br>
 * 왜냐하면 필드값이 들어가면 두개 이상의 필드로 조합되는 조건을 사용하지 못하기 때문이다.
 */
public class QueryPart {
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
	public QueryPart(String field, String value, AnalyzerType analyzer, Occur occur) {
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
		return "QueryPart [field=" + field + ", value=" + value + ", analyzer=" + analyzer + ", occur=" + occur + "]";
	}
}