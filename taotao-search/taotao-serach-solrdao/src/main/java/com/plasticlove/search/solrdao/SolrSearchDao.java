package com.plasticlove.search.solrdao;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SolrSearchDao {
    SolrServer solrServer = new HttpSolrServer("http://121.248.55.152：8081/solr/core1");

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext-solr.xml");
    applicationContext.getBean();

}
