package com.flipkart.daily.controller;

import com.flipkart.daily.dto.SearchRequest;
import com.flipkart.daily.dto.SearchResponse;
import com.flipkart.daily.model.InventoryItem;
import com.flipkart.daily.model.SearchCriteria;
import com.flipkart.daily.model.SortCriteria;
import com.flipkart.daily.service.InventoryService;
import com.flipkart.daily.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;
    private final SearchService searchService;

    @Autowired
    public InventoryController(InventoryService inventoryService, SearchService searchService) {
        this.inventoryService = inventoryService;
        this.searchService = searchService;
    }

    @PostMapping("/items")
    public ResponseEntity<Map<String, String>> addItem(
            @RequestParam String brand,
            @RequestParam String category,
            @RequestParam double price) {

        inventoryService.addItem(brand, category, price);

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Item added successfully");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/inventory")
    public ResponseEntity<Map<String, String>> addInventory(
            @RequestParam String brand,
            @RequestParam String category,
            @RequestParam int quantity) {

        inventoryService.addInventory(brand, category, quantity);

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Inventory added successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/items")
    public ResponseEntity<List<InventoryItem>> getAllItems() {
        return ResponseEntity.ok(inventoryService.getAllInventoryItems());
    }

    @PostMapping("/search")
    public ResponseEntity<SearchResponse> searchItems(@RequestBody SearchRequest request) {
        SearchCriteria searchCriteria = request.toSearchCriteria();
        SortCriteria sortCriteria = request.toSortCriteria();

        List<InventoryItem> items = searchService.searchItems(searchCriteria, sortCriteria);

        return ResponseEntity.ok(new SearchResponse(items));
    }

    // Simplified search endpoints for specific search criteria
    @GetMapping("/search/brand/{brand}")
    public ResponseEntity<SearchResponse> searchByBrand(@PathVariable String brand) {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("brand", Collections.singletonList(brand));

        SearchRequest request = new SearchRequest(criteria, null);

        List<InventoryItem> items = searchService.searchItems(
                request.toSearchCriteria(),
                new SortCriteria()
        );

        return ResponseEntity.ok(new SearchResponse(items));
    }

    @GetMapping("/search/category/{category}")
    public ResponseEntity<SearchResponse> searchByCategory(@PathVariable String category) {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("category", Collections.singletonList(category));

        SearchRequest request = new SearchRequest(criteria, null);

        List<InventoryItem> items = searchService.searchItems(
                request.toSearchCriteria(),
                new SortCriteria()
        );

        return ResponseEntity.ok(new SearchResponse(items));
    }

    @GetMapping("/search/price")
    public ResponseEntity<SearchResponse> searchByPriceRange(
            @RequestParam(required = false) Double from,
            @RequestParam(required = false) Double to) {

        Map<String, Object> criteria = new HashMap<>();
        criteria.put("price", List.of(from, to));

        SearchRequest request = new SearchRequest(criteria, null);

        List<InventoryItem> items = searchService.searchItems(
                request.toSearchCriteria(),
                new SortCriteria()
        );

        return ResponseEntity.ok(new SearchResponse(items));
    }
}