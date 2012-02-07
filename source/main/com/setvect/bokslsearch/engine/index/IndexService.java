package com.setvect.bokslsearch.engine.index;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexReader.FieldOption;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.util.Version;

import com.setvect.bokslsearch.engine.SearchAppLogger;
import com.setvect.bokslsearch.engine.SearchAppUtil;
import com.setvect.bokslsearch.engine.search.QueryPart;
import com.setvect.bokslsearch.engine.search.SearchService;
import com.setvect.bokslsearch.engine.vo.DocRecord;
import com.setvect.bokslsearch.engine.vo.DocRecord.DocField;
import com.setvect.common.util.FileUtil;
import com.setvect.common.util.StringUtilAd;

/**
 * 색인 수행
 */
public class IndexService {
	// not instance
	private IndexService() {
	}

	/**
	 * 색인 수행<br>
	 * TODO 두개 이상의 스래드가 하나의 색인파일에 대해서 색인하는 것 고려 하지 않음
	 * 
	 * @param indexName
	 *            색인 이름
	 * @param data
	 *            색인할 데이터
	 * @throws IOException
	 */
	public static void indexDocument(String indexName, DocRecord data) throws IOException {
		List<DocRecord> da = new ArrayList<DocRecord>();
		da.add(data);
		indexDocument(indexName, da);
	}

	/**
	 * 색인 수행<br>
	 * TODO 두개 이상의 스래드가 하나의 색인파일에 대해서 색인하는 것 고려 하지 않음
	 * 
	 * @param indexName
	 *            색인 이름
	 * @param data
	 *            색인할 데이터
	 * @throws IOException
	 */
	public static void indexDocument(String indexName, List<DocRecord> data) throws IOException {

		File indexFile = SearchAppUtil.getIndexDir(indexName);
		Directory index;
		IndexWriter w = null;
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_35, analyzer);
		try {
			index = new NIOFSDirectory(indexFile);
			w = new IndexWriter(index, config);

			for (DocRecord r : data) {
				Document doc = new Document();
				List<DocField> fs = r.getFields();
				for (DocField f : fs) {
					SearchAppLogger.out.debug("Index: " + f);
					if (StringUtilAd.isEmpty(f.getValue())) {
						continue;
					}
					// 저장용도
					doc.add(new Field(f.getName(), f.getValue(), Field.Store.YES, Field.Index.NOT_ANALYZED));
					// 색인 용도
					Analyzer fieldAnalsyzer = f.getAnalyzerType().getAnalyzer();
					TokenStream ts = fieldAnalsyzer.tokenStream("dummy", new StringReader(f.getValue()));

					doc.add(new Field(f.getName(), ts));
				}
				w.addDocument(doc);
			}
		} finally {
			try {
				if (w != null) {
					w.close();
				}
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 인덱스 삭제<br>
	 * TODO 인덱스, 검색중에 삭제되는 상황 고려 하지 않음
	 * 
	 * @param indexName
	 *            색인 이름
	 */
	public static void deleteIndex(String indexName) {
		File s = SearchAppUtil.getIndexDir(indexName);
		try {
			FileUtil.deleteDirectory(s);
		} catch (IOException e) {
			SearchAppLogger.out.warn(e);
		}
	}

	/**
	 * 색인 정보
	 * 
	 * @param indexName
	 *            색인 이름
	 * @return 색인 정보
	 * @throws IOException
	 */
	public static IndexMetadata getIndexInfo(String indexName) throws IOException {
		File s = SearchAppUtil.getIndexDir(indexName);
		NIOFSDirectory index = new NIOFSDirectory(s);
		IndexReader ir = null;

		IndexMetadata result = new IndexMetadata();
		try {
			ir = IndexReader.open(index);
			result.setDocCount(ir.numDocs());
			result.setDeleteCount(ir.numDeletedDocs());
			Collection<String> fields = ir.getFieldNames(FieldOption.ALL);
			List<String> fieldName = new ArrayList<String>();
			fieldName.addAll(fields);
			result.setFieldName(fieldName);
			result.setSize(FileUtil.sizeOfDirectory(s));
		} finally {
			if (ir != null) {
				ir.close();
			}
		}
		return result;
	}

	/**
	 * 색인안에 있는 문서 삭제
	 * 
	 * @param indexName
	 *            색인명
	 * @param query
	 *            삭제조건(조건에 해당되는 문서를 삭제)
	 * @throws ParseException
	 * @throws IOException
	 */
	public static void deleteDocument(String indexName, QueryPart query) throws IOException, ParseException {
		List<QueryPart> condition = new ArrayList<QueryPart>();
		condition.add(query);
		deleteDocument(indexName, condition);
	}

	/**
	 * 색인안에 있는 문서 삭제
	 * 
	 * @param indexName
	 *            색인명
	 * @param condition
	 *            복합 삭제조건(조건에 해당되는 문서를 삭제)
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void deleteDocument(String indexName, List<QueryPart> condition) throws IOException, ParseException {
		File s = SearchAppUtil.getIndexDir(indexName);
		NIOFSDirectory index = new NIOFSDirectory(s);
		IndexWriter iw = null;
		try {
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_35, analyzer);
			iw = new IndexWriter(index, config);
			BooleanQuery totalQuery = SearchService.parseQuery(condition);
			iw.deleteDocuments(totalQuery);
		} finally {
			if (iw != null) {
				iw.close();
			}
		}
	}
}