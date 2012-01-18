package com.setvect.bokslsearch.engine.index;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexReader.FieldOption;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.util.Version;

import com.setvect.bokslsearch.engine.config.ApplicationConstant;
import com.setvect.bokslsearch.engine.index.DocRecord.DocField;
import com.setvect.common.log.LogPrinter;
import com.setvect.common.util.FileUtil;
import com.setvect.common.util.StringUtilAd;

/**
 * 색인 수행
 */
public class IndexService {
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
	public void indexDocument(String indexName, List<DocRecord> data) throws IOException {
		StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_35, analyzer);

		File indexFile = getIndexDir(indexName);
		Directory index;
		IndexWriter w = null;

		try {
			index = new NIOFSDirectory(indexFile);
			w = new IndexWriter(index, config);
			for (DocRecord r : data) {
				Document doc = new Document();
				List<DocField> fs = r.getFields();
				for (DocField f : fs) {
					LogPrinter.out.debug("Index: " + f);
					if (StringUtilAd.isEmpty(f.getValue())) {
						continue;
					}
					doc.add(new Field(f.getName(), f.getValue(), Field.Store.YES, Field.Index.ANALYZED));
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
	public void deleteIndex(String indexName) {
		File s = getIndexDir(indexName);
		try {
			FileUtil.deleteDirectory(s);
		} catch (IOException e) {
			LogPrinter.out.warn(e);
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
	public IndexMetadata getIndexInfo(String indexName) throws IOException {
		File s = getIndexDir(indexName);
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
	 * 색인 디렉토리 경로
	 * 
	 * @param indexName
	 *            색인 이름
	 * @return 색인 디렉토리 경로
	 */
	private File getIndexDir(String indexName) {
		File indexFile = new File(ApplicationConstant.INDEX_DIR, indexName);
		return indexFile;
	}
}
