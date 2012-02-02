package com.setvect.bokslsearch.engine.test;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanClause.Occur;
import org.junit.AfterClass;
import org.junit.Test;

import com.setvect.bokslsearch.engine.SearchAppLogger;
import com.setvect.bokslsearch.engine.SearchAppUtil;
import com.setvect.bokslsearch.engine.extract.ExtractorEnum;
import com.setvect.bokslsearch.engine.index.AnalyzerType;
import com.setvect.bokslsearch.engine.index.IndexService;
import com.setvect.bokslsearch.engine.search.QueryParameter;
import com.setvect.bokslsearch.engine.search.SearchService;
import com.setvect.bokslsearch.engine.vo.DocRecord;
import com.setvect.bokslsearch.engine.vo.SearchResult;
import com.setvect.common.date.DateUtil;

public class ���ϻ���_��_�˻�_TestCase extends TestInit {
	private static final String INDEX_NAME = "FILE_INDEX";

	/**
	 * ÷������ ����
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	@Test
	public void ���ϻ���and�˻�() throws IOException, ParseException {
		File dir = new File("doc_sample");
		File[] docs = dir.listFiles();
		for (File f : docs) {
			if (!f.isFile()) {
				continue;
			}
			String ext = FilenameUtils.getExtension(f.getName());
			ExtractorEnum extractorEnum = ExtractorEnum.getExtractor(ext);
			String text = null;
			if (extractorEnum != null) {
				text = extractorEnum.getExtractor().extract(f);
			}
			else {
				SearchAppLogger.out.info("����[" + f.getAbsolutePath() + "]�� �ؽ�Ʈ ���� �������� �ʽ��ϴ�.");
			}
			DocRecord fileInfo = new DocRecord();
			fileInfo.addField(new DocRecord.DocField("NAME", f.getName(), AnalyzerType.CJK));
			if (text != null) {
				fileInfo.addField(new DocRecord.DocField("CONTENT", text, AnalyzerType.CJK));
			}
			Date date = new Date(f.lastModified());
			String dateString = DateUtil.getFormatString(date, "yyyyMMdd");
			fileInfo.addField(new DocRecord.DocField("PATH", f.getCanonicalPath(), AnalyzerType.KEYWORD));
			fileInfo.addField(new DocRecord.DocField("SIZE", String.valueOf(f.length()), AnalyzerType.KEYWORD));
			fileInfo.addField(new DocRecord.DocField("DATE", dateString, AnalyzerType.KEYWORD));
			IndexService.indexDocument(INDEX_NAME, fileInfo);
		}

		QueryParameter query = new QueryParameter();
		query.setIndex(SearchAppUtil.toList(INDEX_NAME));
		query.addQuery("CONTENT", "������", AnalyzerType.CJK, Occur.MUST);
		query.setReturnRange(0, 5);
		query.setReturnFields(SearchAppUtil.toList("NAME,CONTENT,DATE"));

		SearchService searcher = new SearchService();
		SearchResult result = searcher.search(query);

		System.out.printf("��ȸ ������: %,d, ���� ������ ������: %,d\n", result.getTotalHits(), result.getCurrentHits());
		List<Map<String, String>> records = result.getRecords();
		System.out.println("###########################");
		for (Map<String, String> r : records) {
			System.out.printf("%s, %s\n", r.get("NAME"), r.get("DATE"));
		}
	}

	@AfterClass
	public static void ���λ���() {
		IndexService.deleteIndex(INDEX_NAME);
		System.out.println("���� ����");
	}
}