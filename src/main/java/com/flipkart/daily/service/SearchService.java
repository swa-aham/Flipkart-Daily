package com.flipkart.daily.service;

import com.flipkart.daily.model.InventoryItem;
import com.flipkart.daily.model.SearchCriteria;
import com.flipkart.daily.model.SortCriteria;
import com.flipkart.daily.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final InventoryRepository inventoryRepository;

    @Autowired
    public SearchService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<InventoryItem> searchItems(SearchCriteria criteria, SortCriteria sortCriteria) {
        List<InventoryItem> allItems = inventoryRepository.getAllInventoryItems();

        // Apply filters
        List<InventoryItem> filteredItems = allItems.stream()
                .filter(buildPredicate(criteria))
                .collect(Collectors.toList());

        // Apply sorting
        sortItems(filteredItems, sortCriteria);

        return filteredItems;
    }

    private Predicate<InventoryItem> buildPredicate(SearchCriteria criteria) {
        Predicate<InventoryItem> predicate = item -> true; // Start with accepting everything

        // Filter by brand
        if (criteria.getBrands() != null && !criteria.getBrands().isEmpty()) {
            predicate = predicate.and(item ->
                    criteria.getBrands().stream()
                            .anyMatch(brand -> brand.equalsIgnoreCase(item.getItem().getBrand())));
        }

        // Filter by category
        if (criteria.getCategories() != null && !criteria.getCategories().isEmpty()) {
            predicate = predicate.and(item ->
                    criteria.getCategories().stream()
                            .anyMatch(category -> category.equalsIgnoreCase(item.getItem().getCategory())));
        }

        // Filter by price range
        if (criteria.getPriceFrom() != null) {
            predicate = predicate.and(item -> item.getItem().getPrice() >= criteria.getPriceFrom());
        }

        if (criteria.getPriceTo() != null) {
            predicate = predicate.and(item -> item.getItem().getPrice() <= criteria.getPriceTo());
        }

        return predicate;
    }

    private void sortItems(List<InventoryItem> items, SortCriteria sortCriteria) {
        Comparator<InventoryItem> comparator;

        // Create appropriate comparator based on sort field
        if (sortCriteria.getField() == SortCriteria.SortField.PRICE) {
            comparator = Comparator.comparing(item -> item.getItem().getPrice());
        } else { // QUANTITY
            comparator = Comparator.comparing(InventoryItem::getQuantity);
        }

        // Apply sort order
        if (sortCriteria.getOrder() == SortCriteria.SortOrder.DESC) {
            comparator = comparator.reversed();
        }

        // Sort the list
        items.sort(comparator);
    }
}