package com.flipkart.daily.model;

import java.util.List;
import java.util.Map;

public class SearchCriteria {

    private List<String> brands;
    private List<String> categories;
    private Double priceFrom;
    private Double priceTo;

    public SearchCriteria() {
    }

    public SearchCriteria(List<String> brands, List<String> categories, Double priceFrom, Double priceTo) {
        this.brands = brands;
        this.categories = categories;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
    }

    public static SearchCriteria fromMap(Map<String, Object> criteria) {
        SearchCriteria searchCriteria = new SearchCriteria();

        if (criteria.containsKey("brand")) {
            searchCriteria.setBrands((List<String>) criteria.get("brand"));
        }

        if (criteria.containsKey("category")) {
            searchCriteria.setCategories((List<String>) criteria.get("category"));
        }

        if (criteria.containsKey("price")) {
            List<Double> priceRange = (List<Double>) criteria.get("price");
            if (priceRange != null && priceRange.size() >= 1) {
                searchCriteria.setPriceFrom(priceRange.get(0));
            }
            if (priceRange != null && priceRange.size() >= 2) {
                searchCriteria.setPriceTo(priceRange.get(1));
            }
        }

        return searchCriteria;
    }

    public List<String> getBrands() {
        return brands;
    }

    public void setBrands(List<String> brands) {
        this.brands = brands;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Double priceTo) {
        this.priceTo = priceTo;
    }
}
