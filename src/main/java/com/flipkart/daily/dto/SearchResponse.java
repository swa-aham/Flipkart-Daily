package com.flipkart.daily.dto;

import com.flipkart.daily.model.InventoryItem;

import java.util.List;

public class SearchResponse {
    private List<InventoryItem> items;

    public SearchResponse() {
    }

    public SearchResponse(List<InventoryItem> items) {
        this.items = items;
    }

    public List<InventoryItem> getItems() {
        return items;
    }

    public void setItems(List<InventoryItem> items) {
        this.items = items;
    }
}