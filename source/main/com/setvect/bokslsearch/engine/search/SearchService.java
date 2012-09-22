package com.setvect.bokslsearch.engine.search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.util.Version;

import com.setvect.bokslsearch.engine.SearchAppLogger;
import com.setvect.bokslsearch.engine.SearchAppUtil;
import com.setvect.bokslsearch.engine.config.SearchAppConstant;
import com.setvect.bokslsearch.engine.vo.SearchResult;

/**
 * 색인 수행
 */
public class SearchService {
	// not instance
	private SearchService() {
	}

	/**
	 * 색인에 대한 질의 수행
	 * 
	 * @param searchCondtion
	 *            검색 조건
	 * @return 조회 결과
	 * @throws IOException
	 * @throws ParseException
	 */
	public static SearchResult search(QueryParameter searchCondtion) throws IOException, ParseException {

		IndexSearcher searcher = makeSearcher(searchCondtion.getIndex());

		int startPosition = searchCondtion.getStartPosition();
		int numHits = startPosition + searchCondtion.getReturnCount();
		BooleanQuery totalQuery = parseQuery(searchCondtion.getQueries());
		try {
			ScoreDoc[] hits;
			SortField sortField = searchCondtion.getSortField();
			TopDocs topDocs;
			if (sortField != null) {
				Sort sort = new Sort(sortField);
				topDocs = searcher.search(totalQuery, numHits, sort);
				hits = topDocs.scoreDocs;
			}
			else {
				TopScoreDocCollector collector = TopScoreDocCollector.create(numHits, false);
				searcher.search(totalQuery, collector);
				topDocs = collector.topDocs();
				hits = topDocs.scoreDocs;
			}

			SearchResult result = new SearchResult();
			result.setTotalHits(topDocs.totalHits);

			for (int i = startPosition; i < hits.length; i++) {
				int docId = hits[i].doc;
				Document d = searcher.doc(docId);
				SearchAppLogger.out.debug("DOCID: " + docId + ", Document: " + d);
				Map<String, String> record = new HashMap<String, String>();
				List<String> returnFields = searchCondtion.getReturnFields();
				for (String f : returnFields) {
					String v = d.get(f);
					record.put(f, v);
				}

				record.put(SearchAppConstant.RESERVED_FIELD.DOC_ID, String.valueOf(hits[i].doc));
				record.put(SearchAppConstant.RESERVED_FIELD.SCORE, String.valueOf(hits[i].score));
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
	 * 인덱스에 대한 검색 조건 대입
	 * 
	 * @param index
	 *            검색 대상 인덱스
	 * @return 검색기
	 * @throws IOException
	 */
	private static IndexSearcher makeSearcher(List<String> index) throws IOException {
		IndexSearcher searcher = null;
		IndexReader[] ir = getIndexReaders(index);
		MultiReader multiReader = new MultiReader(ir);
		searcher = new IndexSearcher(multiReader);
		return searcher;
	}

	/**
	 * 여러개의 질의 조건에 대해 하나의 조건으로 병합 한다.
	 * 
	 * @param queries
	 *            질의 조건
	 * @return 최종 질의
	 * @throws ParseException
	 */
	public static BooleanQuery parseQuery(List<QueryPart> queries) throws ParseException {
		BooleanQuery totalQuery = new BooleanQuery();
		for (QueryPart qp : queries) {
			QueryParser queryParser = new QueryParser(Version.LUCENE_35, "", qp.getAnalyzer().getAnalyzer());
			Query q = queryParser.parse(qp.getClause());
			totalQuery.add(q, qp.getOccur());
		}

		SearchAppLogger.out.debug("[Query] " + totalQuery.toString());
		return totalQuery;
	}

	/**
	 * 인덱스 이름에 대한 Reader를 제공
	 * 
	 * @param index
	 *            인덱스 이름
	 * @return Index Reader
	 * @throws IOException
	 */
	private static IndexReader[] getIndexReaders(List<String> index) throws IOException {
		List<IndexReader> indexReader = new ArrayList<IndexReader>();
		for (String indexDirectory : index) {
			File indexDir = SearchAppUtil.getIndexDir(indexDirectory);
			Directory dir = NIOFSDirectory.open(indexDir);
			IndexReader reader = IndexReader.open(dir, true);
			indexReader.add(reader);
		}
		IndexReader[] ir = indexReader.toArray(new IndexReader[0]);
		return ir;
	}
}
