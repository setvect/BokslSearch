package com.setvect.bokslsearch.engine.search;

import org.apache.lucene.search.BooleanClause.Occur;

import com.setvect.bokslsearch.engine.index.AnalyzerType;
import com.setvect.common.util.StringUtilAd;

/**
 * ���� ���� ����<br>
 * �ʵ尪 �Է¾��� value���� �������� �� ���� �ִ�.<br>
 * �ֳ��ϸ� �ʵ尪�� ���� �ΰ� �̻��� �ʵ�� ���յǴ� ������ ������� ���ϱ� �����̴�.
 */
public class QueryPart {
	/** �ʵ� �̸� */
	private final String field;
	/** �˻� �� */
	private final String value;
	/** ���м� */
	private final AnalyzerType analyzer;
	/** ���� ���� */
	private final Occur occur;

	/**
	 * @param field
	 *            �ʵ� �̸�
	 * @param value
	 *            �˻� ��
	 * @param analyzer
	 *            ���м�
	 * @param occur
	 *            ���� ����
	 */
	public QueryPart(String field, String value, AnalyzerType analyzer, Occur occur) {
		this.field = field;
		this.value = value;
		this.analyzer = analyzer;
		this.occur = occur;
	}

	/**
	 * �ʵ� �̸�
	 * 
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * �˻� ��
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * ���� ����
	 * 
	 * @return the occur
	 */
	public Occur getOccur() {
		return occur;
	}

	/**
	 * ���м�
	 * 
	 * @return the analyzer
	 */
	public AnalyzerType getAnalyzer() {
		return analyzer;
	}

	/**
	 * Lucene�� �����ϴ� ������
	 * 
	 * @return ������
	 */
	public String getClause() {
		if (StringUtilAd.isEmpty(field)) {
			// �ʵ尪 �Է¾��� value���� �������� �� ���� �ִ�.
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