package com.setvect.bokslsearch.engine.extract;

/**
 * ���� ����(Ȯ����)���� �ؽ�Ʈ�� ���� �� �� �ִ� �ν��Ͻ��� ����
 */
public enum ExtractorEnum {
	DOC {
		@Override
		public FileTextExtractor getExtractor() {
			return new DocExtractor();
		}
	},
	DOCX {
		@Override
		public FileTextExtractor getExtractor() {
			return new DocxExtractor();
		}
	},

	XLS {
		@Override
		public FileTextExtractor getExtractor() {
			return new XlsExtractor();
		}
	},
	XLSX {
		@Override
		public FileTextExtractor getExtractor() {
			return new XlsxExtractor();
		}
	},
	PPT {
		@Override
		public FileTextExtractor getExtractor() {
			return new PptExtractor();
		}
	},
	PPTX {
		@Override
		public FileTextExtractor getExtractor() {
			return new PptxExtractor();
		}
	},
	PDF {
		@Override
		public FileTextExtractor getExtractor() {
			return new PdfExtractor();
		}
	},
	;
	/**
	 * �ؽ�Ʈ �����
	 * 
	 * @return �ؽ�Ʈ �����
	 */
	public abstract FileTextExtractor getExtractor();
}
