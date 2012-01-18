package com.setvect.bokslsearch.engine.index;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;

import com.setvect.bokslsearch.engine.config.ApplicationConstant;
import com.setvect.bokslsearch.engine.index.DocRecord.DocField;
import com.setvect.common.log.LogPrinter;
import com.setvect.common.util.FileUtil;
import com.setvect.common.util.StringUtilAd;

/**
 * ���� ����
 */
@Service("IndexService")
public class IndexService {
	/**
	 * ���� ����
	 * 
	 * @param indexName
	 *            ���� �̸�
	 * @param data
	 *            ������ ������
	 * @throws IOException
	 */
	public void indexDocument(String indexName, List<DocRecord> data) throws IOException {
		StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_35, analyzer);

		File indexFile = getIndeFile(indexName);
		Directory index = new NIOFSDirectory(indexFile);
		IndexWriter w = null;

		try {
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
			w.close();
		}
	}

	/**
	 * �ε��� ����<br>
	 * TODO �ε���, �˻��߿� �����Ǵ� ��Ȳ ��� ���� ����
	 * 
	 * @param indexName
	 *            ���� �̸�
	 */
	public void deleteIndex(String indexName) {
		File s = getIndeFile(indexName);
		try {
			FileUtil.deleteDirectory(s);
		} catch (IOException e) {
			LogPrinter.out.warn(e);
		}
	}

	/**
	 * ���� ���丮 ���
	 * 
	 * @param indexName
	 *            ���� �̸�
	 * @return ���� ���丮 ���
	 */
	private File getIndeFile(String indexName) {
		File indexFile = new File(ApplicationConstant.INDEX_DIR, indexName);
		return indexFile;
	}
}
