package com.plasticlove.manage.service.impl;


import com.plasticlove.commons.ItemIndexDB;

import com.plasticlove.commons.TaotaoResult;
import com.plasticlove.manage.service.SearchManageService;
import com.plasticlove.mapper.SearchMapper;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SearchManageServiceImpl implements SearchManageService {

    @Autowired
    private SearchMapper searchMapper;
    @Autowired
    private SolrServer solrServer;

    @Override
    public TaotaoResult importItemToIndexDB() throws Exception{
        List<ItemIndexDB> itemIndexList = searchMapper.selectIndexItem();

        for (ItemIndexDB itemIndexDB : itemIndexList) {
            SolrInputDocument solrDocument = new SolrInputDocument();
            solrDocument.addField("id",itemIndexDB.getId().toString());
            solrDocument.addField("item_title",itemIndexDB.getTitle());
            solrDocument.addField("item_sell_point",itemIndexDB.getSell_point());
            solrDocument.addField("item_price",itemIndexDB.getPrice());
            solrDocument.addField("item_image",itemIndexDB.getImage());
            solrDocument.addField("item_category_name",itemIndexDB.getCategory_name());
            solrDocument.addField("item_desc",itemIndexDB.getItem_desc());
            solrServer.add(solrDocument);
        }
        solrServer.commit();
        return TaotaoResult.ok();
    }
}
