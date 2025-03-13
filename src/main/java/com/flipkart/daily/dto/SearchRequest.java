package com.flipkart.daily.dto;

import com.flipkart.daily.model.SearchCriteria;
import com.flipkart.daily.model.SortCriteria;

import java.util.Map;

public class SearchRequest {
    private Map<String, Object> criteria;
    private Object[] orderBy;

    public SearchRequest() {
    }

    public SearchRequest(Map<String, Object> criteria, Object[] orderBy) {
        this.criteria = criteria;
        this.orderBy = orderBy;
    }

    public Map<String, Object> getCriteria() {
        return criteria;
    }

    public void setCriteria(Map<String, Object> criteria) {
        this.criteria = criteria;
    }

    public Object[] getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Object[] orderBy) {
        this.orderBy = orderBy;
    }

    public SearchCriteria toSearchCriteria() {
        return SearchCriteria.fromMap(criteria);
    }

    public SortCriteria toSortCriteria() {
        return SortCriteria.fromArray(orderBy);
    }
}