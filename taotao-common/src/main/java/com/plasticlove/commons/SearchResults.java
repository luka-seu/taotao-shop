package com.plasticlove.commons;

import java.util.List;

public class SearchResults {
    private List<ItemIndexDB> itemList;// 搜索结果列表
    private long recordCount;// 总记录数
    private long pageCount;// 总页数
    public List<ItemIndexDB> getItemList() {
        return itemList;
    }
    public void setItemList(List<ItemIndexDB> itemList) {
        this.itemList = itemList;
    }
    public long getRecordCount() {
        return recordCount;
    }
    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }
    public long getPageCount() {
        return pageCount;
    }
    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }
}
