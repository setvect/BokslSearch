package com.setvect.bokslsearch.engine.index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 색인을 하기 위한 레코드(문서)
 */
public class DocRecord {
	/** 색인 대상 필드 */
	private List<DocField> fields = new ArrayList<DocRecord.DocField>();

	/**
	 * 색인 대상 필드
	 * 
	 * @return 색인 대상 필드
	 */
	public List<DocField> getFields() {
		return Collections.unmodifiableList(fields);
	}

	/**
	 * 필드 등록
	 * 
	 * @param field
	 *            색인 대상 필드
	 */
	public void addField(DocField field) {
		this.fields.add(field);
	}

	/**
	 * 색인 대상 필드 정의<br>
	 * TODO 색인 타입, 저장 유형 등 메타정보는 나중에 설정
	 */
	public static class DocField {
		/** 필드 이름 */
		private String name;
		/** 필드 값 */
		private String value;

		/**
		 * 필드 이름
		 * 
		 * @return 필드 이름
		 */
		public String getName() {
			return name;
		}

		/**
		 * 필드 이름
		 * 
		 * @param name
		 *            필드 이름
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * 필드 값
		 * 
		 * @return 필드 값
		 */
		public String getValue() {
			return value;
		}

		/**
		 * 필드 값
		 * 
		 * @param value
		 *            필드 값
		 */
		public void setValue(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "DocField [name=" + name + ", value=" + value + "]";
		}
	}
}
