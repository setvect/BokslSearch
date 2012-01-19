package com.setvect.bokslsearch.engine.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * �˻����
 */
public class SearchResult {
	/** �˻��� ���� �� �� */
	private int totalHits;
	/** ��ȸ�� ��� �� */
	private List<Map<String, String>> records = new ArrayList<Map<String, String>>();

	/**
	 * �˻��� ���� �� ��
	 * 
	 * @return �˻��� ���� �� ��
	 */
	public int getTotalHits() {
		return totalHits;
	}

	/**
	 * �˻��� ���� �� ��
	 * 
	 * @param totalHits
	 *            �˻��� ���� �� ��
	 */
	public void setTotalHits(int docTotalCount) {
		this.totalHits = docTotalCount;
	}

	/**
	 * ���� �������� ���Ͽ� �˻��� ���� ��
	 * 
	 * @return ���� �������� ���Ͽ� �˻��� ���� ��
	 */
	public int getCurrentHits() {
		return records.size();
	}

	/**
	 * ��ȸ�� ��� ��
	 * 
	 * @return ��ȸ�� ��� ��
	 */
	public List<Map<String, String>> getRecords() {
		return records;
	}

	/**
	 * ��ȸ�� ��� ��
	 * 
	 * @param records
	 *            ��ȸ�� ��� ��
	 */
	public void addRecord(Map<String, String> record) {
		this.records.add(record);
	}

	/**
	 * @param rowNum
	 *            �� ��ȣ 0 ���� ����
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
