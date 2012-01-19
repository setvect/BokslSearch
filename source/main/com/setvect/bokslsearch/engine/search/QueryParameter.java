package com.setvect.bokslsearch.engine.search;

import java.util.List;

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
	private String query;

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
	 * ��� ������ ����
	 * 
	 * @return ��� ������ ����
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
	public String getQuery() {
		return query;
	}

	/**
	 * ���� ����
	 * 
	 * @param query
	 *            ���� ����
	 */
	public void setQuery(String query) {
		this.query = query;
	}
}
