package com.setvect.bokslsearch.engine.index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ������ �ϱ� ���� ���ڵ�(����)
 */
public class DocRecord {
	/** ���� ��� �ʵ� */
	private List<DocField> fields = new ArrayList<DocRecord.DocField>();

	/**
	 * ���� ��� �ʵ�
	 * 
	 * @return ���� ��� �ʵ�
	 */
	public List<DocField> getFields() {
		return Collections.unmodifiableList(fields);
	}

	/**
	 * �ʵ� ���
	 * 
	 * @param field
	 *            ���� ��� �ʵ�
	 */
	public void addField(DocField field) {
		this.fields.add(field);
	}

	/**
	 * ���� ��� �ʵ� ����<br>
	 * TODO ���� Ÿ��, ���� ���� �� ��Ÿ������ ���߿� ����
	 */
	public static class DocField {
		/** �ʵ� �̸� */
		private String name;
		/** �ʵ� �� */
		private String value;

		/**
		 * �ʵ� �̸�
		 * 
		 * @return �ʵ� �̸�
		 */
		public String getName() {
			return name;
		}

		/**
		 * �ʵ� �̸�
		 * 
		 * @param name
		 *            �ʵ� �̸�
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * �ʵ� ��
		 * 
		 * @return �ʵ� ��
		 */
		public String getValue() {
			return value;
		}

		/**
		 * �ʵ� ��
		 * 
		 * @param value
		 *            �ʵ� ��
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
