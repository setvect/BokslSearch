package com.setvect.bokslsearch.engine.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.search.BooleanClause.Occur;

import com.setvect.bokslsearch.engine.index.AnalyzerType;
import com.setvect.common.util.StringUtilAd;

/**
 * �˻� ����<br>
 */
public class QueryParameter {
	/** �˻� ��� ���� */
	private List<String> index;

	/** ��ȸ �ʵ� */
	private List<String> returnFields;

	/** �˻� ����� ������ ��ġ. 0 ���� ���� */
	private int startPosition;

	/** ��� ������ ���� */
	private int returnCount;

	/** ���� ���� */
	private List<QueryPart> queries = new ArrayList<QueryParameter.QueryPart>();

	/**
	 * �˻� ��� ����
	 * 
	 * @return �˻� ��� ����
	 */
	public List<String> getIndex() {
		return index;
	}

	/**
	 * �˻� ��� ����
	 * 
	 * @param index
	 *            �˻� ��� ����
	 */
	public void setIndex(List<String> index) {
		this.index = index;
	}

	/**
	 * �˻� ����� ������ ��ġ. 0 ���� ����
	 * 
	 * @return �˻� ����� ������ ��ġ. 0 ���� ����
	 */
	public List<String> getReturnFields() {
		return returnFields;
	}

	/**
	 * �˻� ����� ������ ��ġ. 0 ���� ����
	 * 
	 * @param returnFields
	 *            �˻� ����� ������ ��ġ. 0 ���� ����
	 */
	public void setReturnFields(List<String> returnFields) {
		this.returnFields = returnFields;
	}

	/**
	 * ��� ������ ����<br>
	 * ����� ������ ������ startPosition ~ startPosition + returnCount - 1
	 * 
	 * @param startPosition
	 *            ��� ������ ����
	 * @param returnCount
	 *            ��� ������ ����
	 */
	public void setReturnRange(int startPosition, int returnCount) {
		this.startPosition = startPosition;
		this.returnCount = returnCount;
	}

	/**
	 * �˻� ����� ������ ��ġ. 0 ���� ����
	 * 
	 * @return �˻� ����� ������ ��ġ. 0 ���� ����
	 */
	public int getStartPosition() {
		return startPosition;
	}

	/**
	 * ��� ������ ����
	 * 
	 * @return ��� ������ ����
	 */
	public int getReturnCount() {
		return returnCount;
	}

	/**
	 * ���� ����
	 * 
	 * @return ���� ����
	 */
	public List<QueryPart> getQueries() {
		return queries;
	}

	/**
	 * ���� ���� ���
	 * 
	 * @param field
	 *            �ʵ� �̸�
	 * @param value
	 *            �˻� ��
	 * @param analyzer
	 *            �м���
	 */
	public void addQuery(String field, String value, AnalyzerType analyzer) {
		addQuery(value, value, analyzer, Occur.MUST);
	}

	/**
	 * ���� ���� ���
	 * 
	 * @param field
	 *            �ʵ� �̸�
	 * @param value
	 *            �˻� ��
	 * @param analyzer
	 *            �м���
	 * @param occur
	 *            ���� ����
	 */
	public void addQuery(String field, String value, AnalyzerType analyzer, Occur occur) {
		queries.add(new QueryPart(field, value, analyzer, occur));
	}

	/**
	 * ���� ���� ����<br>
	 * �ʵ尪 �Է¾��� value���� �������� �� ���� �ִ�.<br>
	 * �ֳ��ϸ� �ʵ尪�� ���� �ΰ� �̻��� �ʵ�� ���յǴ� ������ ������� ���ϱ� �����̴�.
	 */
	public static class QueryPart {
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
		private QueryPart(String field, String value, AnalyzerType analyzer, Occur occur) {
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
			return "QueryPart [field=" + field + ", value=" + value + ", analyzer=" + analyzer + ", occur=" + occur
					+ "]";
		}
	}
}
