package com.setvect.bokslsearch.engine.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.search.SortField;
import org.apache.lucene.search.BooleanClause.Occur;

import com.setvect.bokslsearch.engine.index.AnalyzerType;

/**
 * �˻� ����<br>
 * ���� �ʵ�� �ϳ��� ������ �� ����<br>
 * ���� ������ ���� ��� ��Ȯ�� ������ ����
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
	private List<QueryPart> queries = new ArrayList<QueryPart>();

	/** ���� ���� */
	private SortField sortField;

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
	 * ���� ���� ���
	 * 
	 * @param query
	 *            ����
	 */
	public void addQuery(QueryPart query) {
		queries.add(query);
	}

	/**
	 * ���� ����
	 * 
	 * @param fieldName
	 *            ���� �ʵ�
	 * @param asc
	 *            true: �������� ����, false: �������� ����
	 */
	public void setSortField(String field, boolean asc) {
		sortField = new SortField(field, SortField.STRING, asc);
	}

	/**
	 * ���� ����
	 * 
	 * @param sortField
	 *            ���� ����
	 */
	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	/**
	 * ���� ����
	 * 
	 * @return ���� ����
	 */
	public SortField getSortField() {
		return sortField;
	}

}
