package com.plasticlove.search.service;


import com.plasticlove.commons.SearchResults;

public interface SearchService {
    public SearchResults searchKeywords(String keywords,Integer page,Integer rows);
}
