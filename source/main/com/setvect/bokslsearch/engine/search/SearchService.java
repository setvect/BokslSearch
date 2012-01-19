package com.setvect.bokslsearch.engine.search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.util.Version;

import com.setvect.bokslsearch.engine.ApplicationUtil;
import com.setvect.bokslsearch.engine.vo.SearchResult;
import com.setvect.common.log.LogPrinter;

/**
 * ���� ����
 */
public class SearchService {
	private static final int GET_TOP = 10;

	/**
	 * ������ �˻��� ����<br>
	 * �׽�Ʈ ��
	 * 
	 * @param indexName
	 *            ���� �̸�
	 * @param filed
	 *            �ʵ��
	 * @param query
	 *            Ű����
	 * @param returnFields
	 *            ���ϵ� �ʵ�
	 * @return ��ȸ ���
	 * @throws ParseException
	 * @throws IOException
	 */
	public SearchResult searchSample(String indexName, String filed, String query, List<String> returnFields)
			throws ParseException, IOException {
		StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);

		Query q = new QueryParser(Version.LUCENE_35, filed, analyzer).parse(query);
		File indexFile = ApplicationUtil.getIndexDir(indexName);
		Directory directory = new NIOFSDirectory(indexFile);

		IndexSearcher searcher = null;
		try {
			IndexReader ir = IndexReader.open(directory, true);
			searcher = new IndexSearcher(ir);
			TopScoreDocCollector collector = TopScoreDocCollector.create(GET_TOP, true);
			searcher.search(q, collector);
			TopDocs topDocs = collector.topDocs();
			ScoreDoc[] hits = topDocs.scoreDocs;
			SearchResult result = new SearchResult();
			result.setTotalHits(topDocs.totalHits);
			
			for (int i = 0; i < hits.length; i++) {
				int docId = hits[i].doc;
				Document d = searcher.doc(docId);
				LogPrinter.out.debug("DOCID: " + docId + ", Document: " + d);
				Map<String, String> record = new HashMap<String, String>();
				for (String f : returnFields) {
					String v = d.get(f);
					record.put(f, v);
				}
				result.addRecord(record);
			}
			return result;
		} finally {
			if (searcher != null) {
				searcher.close();
			}
		}
	}

	/**
	 * ���ο� ���� ���� ����
	 * 
	 * @param query
	 *            �˻� ����
	 * @return ��ȸ ���
	 * @throws IOException
	 * @throws ParseException
	 */
	public SearchResult search(QueryParameter query) throws IOException, ParseException {
		List<String> index = query.getIndex();
		IndexReader[] ir = getIndexReaders(index);

		MultiReader multiReader = new MultiReader(ir);
		int numHits = query.getStartPosition() + query.getReturnCount();
		TopScoreDocCollector collector = TopScoreDocCollector.create(numHits, false);

		Term t = new Term("TITLE", "ambitious");
		Query q = new TermQuery(t);
		// StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
		// Query q = new QueryParser(Version.LUCENE_35, "TITLE",
		// analyzer).parse("ambitious");
		IndexSearcher searcher = new IndexSearcher(multiReader);
		try {
			searcher.search(q, collector);
			ScoreDoc[] hits = collector.topDocs().scoreDocs;
			SearchResult result = new SearchResult();

			for (int i = 0; i < hits.length; i++) {
				int docId = hits[i].doc;
				Document d = searcher.doc(docId);
				LogPrinter.out.debug("DOCID: " + docId + ", Document: " + d);
				Map<String, String> record = new HashMap<String, String>();
				List<String> returnFields = query.getReturnFields();
				for (String f : returnFields) {
					String v = d.get(f);
					record.put(f, v);
				}
				result.addRecord(record);
			}
			return result;
		} finally {
			if (searcher != null) {
				searcher.close();
			}
		}
	}

	/**
	 * �ε��� �̸��� ���� Reader�� ����
	 * 
	 * @param index
	 *            �ε��� �̸�
	 * @return Index Reader
	 * @throws IOException
	 */
	private IndexReader[] getIndexReaders(List<String> index) throws IOException {
		List<IndexReader> indexReader = new ArrayList<IndexReader>();
		for (String indexDirectory : index) {
			File indexDir = ApplicationUtil.getIndexDir(indexDirectory);
			Directory dir = NIOFSDirectory.open(indexDir);
			IndexReader reader = IndexReader.open(dir, true);
			indexReader.add(reader);
		}
		IndexReader[] ir = indexReader.toArray(new IndexReader[0]);
		return ir;
	}
}
