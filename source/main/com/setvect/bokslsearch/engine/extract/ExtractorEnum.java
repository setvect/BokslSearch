package com.setvect.bokslsearch.engine.extract;

/**
 * 파일 종류(확장자)별로 텍스트를 추출 할 수 있는 인스턴스를 제공
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
	 * 텍스트 추출기
	 * 
	 * @return 텍스트 추출기
	 */
	public abstract FileTextExtractor getExtractor();

	/**
	 * 확장자에 따른 텍스트 추출기를 제공<br>
	 * 
	 * @param ext
	 *            확장자 (예: doc, xlsx, pdf, ...)<br>
	 * @return 지원 안되는 확장자는 null
	 */
	public static ExtractorEnum getExtractor(String ext) {
		String t = ext.toUpperCase();
		try {
			return ExtractorEnum.valueOf(t);
		} catch (Exception e) {
			return null;
		}
	}
}
