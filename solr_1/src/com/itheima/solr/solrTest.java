package com.itheima.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class solrTest {

	@Test
	public void testAdd() throws Exception {
		SolrServer solrService = new HttpSolrServer("http://localhost:8080/solr/collection1");
		for (int i = 1; i < 11; i++) {
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("id", i+"");
			doc.addField("name", "solr标题"+i);
			doc.addField("content", "solr内容"+i);
			solrService.add(doc);
		}
		solrService.commit();
		
	}
	
	@Test
	public void testUpdate() throws Exception {
		SolrServer solrService = new HttpSolrServer("http://localhost:8080/solr/collection1");
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", "1");
		doc.addField("name", "solr标题修改后");
		doc.addField("content", "solr内容修改后");
		solrService.add(doc);
		solrService.commit();
	}
	
	@Test
	public void testDel() throws Exception {
		SolrServer solrService = new HttpSolrServer("http://localhost:8080/solr/collection1");
		//solrService.deleteById("3");
		solrService.deleteByQuery("name:solr标题5");
		solrService.commit();
	}
	
	@Test
	public void testQuery() throws Exception {
		SolrServer solrService = new HttpSolrServer("http://localhost:8080/solr/collection1");
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("name:solr");
		QueryResponse query = solrService.query(solrQuery);
		SolrDocumentList results = query.getResults();
		System.out.println("总条数："+results.getNumFound());
		for (SolrDocument solrDocument : results) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("name"));
			System.out.println(solrDocument.get("content"));
		}
	}
	
}
