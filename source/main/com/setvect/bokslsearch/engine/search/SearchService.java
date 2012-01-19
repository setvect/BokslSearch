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
 * 색인 수행
 */
public class SearchService {
	private static final int GET_TOP = 10;

	/**
	 * 간단한 검색을 수행<br>
	 * 테스트 용
	 * 
	 * @param indexName
	 *            색인 이름
	 * @param filed
	 *            필드명
	 * @param query
	 *            키워드
	 * @param returnFields
	 *            리턴될 필드
	 * @return 조회 결과
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
	 * 색인에 대한 질의 수행
	 * 
	 * @param query
	 *            검색 조건
	 * @return 조회 결과
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
			SearchResult result = new SearchResult();
			TopDocs topDocs = collector.topDocs();
			ScoreDoc[] hits = topDocs.scoreDocs;
			result.setTotalHits(topDocs.totalHits);
			
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
	 * 인덱스 이름에 대한 Reader를 제공
	 * 
	 * @param index
	 *            인덱스 이름
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
