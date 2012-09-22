package com.setvect.bokslsearch.engine.index;

import java.util.List;

/**
 * 인덱스에 대한 메타정보
 */
public class IndexMetadata {
	/** 색인 문서 건수 */
	private int docCount;

	/** 삭제 문서 수 */
	private int deleteCount;

	/** 색인 필드 이름 */
	private List<String> fieldName;
	
	/** 색인 파일 사이즈 */
	private long size;

	/**
	 * 색인 문서 건수
	 * 
	 * @return 색인 문서 건수
	 */
	public int getDocCount() {
		return docCount;
	}

	/**
	 * 색인 문서 건수
	 * 
	 * @param docCount
	 *            색인 문서 건수
	 */
	public void setDocCount(int docCount) {
		this.docCount = docCount;
	}

	/**
	 * 삭제 문서 수
	 * 
	 * @return 삭제 문서 수
	 */
	public int getDeleteCount() {
		return deleteCount;
	}

	/**
	 * 삭제 문서 수
	 * 
	 * @param deleteCount
	 *            삭제 문서 수
	 */
	public void setDeleteCount(int deleteCount) {
		this.deleteCount = deleteCount;
	}

	/**
	 * 색인 필드 이름
	 * 
	 * @return 색인 필드 이름
	 */
	public List<String> getFieldName() {
		return fieldName;
	}

	/**
	 * 색인 필드 이름
	 * 
	 * @param fieldName
	 *            색인 필드 이름
	 */
	public void setFieldName(List<String> fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * 색인 파일 사이즈
	 * 
	 * @return 색인 파일 사이즈
	 */
	public long getSize() {
		return size;
	}

	/**
	 * 색인 파일 사이즈
	 * 
	 * @param size
	 *            색인 파일 사이즈
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
