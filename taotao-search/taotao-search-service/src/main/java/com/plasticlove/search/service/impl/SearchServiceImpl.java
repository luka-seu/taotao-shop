package com.plasticlove.search.service.impl;

import com.plasticlove.commons.SearchResults;
import com.plasticlove.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SolrServer solrServer;
    @Override
    public SearchResults searchKeywords(String keywords,Integer page,Integer rows) {
        SolrQuery query = new SolrQuery();
        if(StringUtils.isNotBlank(keywords)){
            query.setQuery(keywords);
        }else{
            query.setQuery("*:*");
        }
        if(page==null)page=1;
        if(rows==null)rows=60;
        query.setStart((page-1)*rows);
        query.setRows(rows);

        query.setHighlight(true);
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");
        query.addHighlightField("item_title");


        return null;
    }
}
