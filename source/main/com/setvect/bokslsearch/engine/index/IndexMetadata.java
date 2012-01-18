package com.setvect.bokslsearch.engine.index;

import java.util.List;

/**
 * �ε����� ���� ��Ÿ����
 */
public class IndexMetadata {
	/** ���� ���� �Ǽ� */
	private int docCount;

	/** ���� ���� �� */
	private int deleteCount;

	/** ���� �ʵ� �̸� */
	private List<String> fieldName;
	
	/** ���� ���� ������ */
	private long size;

	/**
	 * ���� ���� �Ǽ�
	 * 
	 * @return ���� ���� �Ǽ�
	 */
	public int getDocCount() {
		return docCount;
	}

	/**
	 * ���� ���� �Ǽ�
	 * 
	 * @param docCount
	 *            ���� ���� �Ǽ�
	 */
	public void setDocCount(int docCount) {
		this.docCount = docCount;
	}

	/**
	 * ���� ���� ��
	 * 
	 * @return ���� ���� ��
	 */
	public int getDeleteCount() {
		return deleteCount;
	}

	/**
	 * ���� ���� ��
	 * 
	 * @param deleteCount
	 *            ���� ���� ��
	 */
	public void setDeleteCount(int deleteCount) {
		this.deleteCount = deleteCount;
	}

	/**
	 * ���� �ʵ� �̸�
	 * 
	 * @return ���� �ʵ� �̸�
	 */
	public List<String> getFieldName() {
		return fieldName;
	}

	/**
	 * ���� �ʵ� �̸�
	 * 
	 * @param fieldName
	 *            ���� �ʵ� �̸�
	 */
	public void setFieldName(List<String> fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * ���� ���� ������
	 * 
	 * @return ���� ���� ������
	 */
	public long getSize() {
		return size;
	}

	/**
	 * ���� ���� ������
	 * 
	 * @param size
	 *            ���� ���� ������
	 */
	public void setSize(long size) {
		this.size = size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IndexMetadata [docCount=" + docCount + ", deleteCount=" + deleteCount + ", fieldName=" + fieldName
				+ ", size=" + size + "]";
	}
}
