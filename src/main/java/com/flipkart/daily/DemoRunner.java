package com.flipkart.daily;

import com.flipkart.daily.model.InventoryItem;
import com.flipkart.daily.model.SearchCriteria;
import com.flipkart.daily.model.SortCriteria;
import com.flipkart.daily.service.InventoryService;
import com.flipkart.daily.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DemoRunner implements CommandLineRunner {
    private final InventoryService inventoryService;
    private final SearchService searchService;

    @Autowired
    public DemoRunner(InventoryService inventoryService, SearchService searchService) {
        this.inventoryService = inventoryService;
        this.searchService = searchService;
    }

    @Override
    public void run(String... args) {
        System.out.println("Starting Flipkart Daily Demo...");

        // Add items
        System.out.println("Adding items...");
        inventoryService.addItem("Amul", "Milk", 100);
        inventoryService.addItem("Amul", "Curd", 50);
        inventoryService.addItem("Nestle", "Milk", 60);
        inventoryService.addItem("Nestle", "Curd", 90);

        // Add inventory
        System.out.println("Adding inventory...");
        inventoryService.addInventory("Amul", "Milk", 10);
        inventoryService.addInventory("Nestle", "Milk", 5);
        inventoryService.addInventory("Nestle", "Curd", 10);
        inventoryService.addInventory("Amul", "Milk", 10);
        inventoryService.addInventory("Amul", "Curd", 5);

        // Print all inventory
        System.out.println("Inventory:");
        List<InventoryItem> allItems = inventoryService.getAllInventoryItems();
        allItems.forEach(item -> System.out.println(item.getItem().getBrand() + " -> " +
                item.getItem().getCategory() + " -> " + item.getQuantity()));

        // Search by brand
        System.out.println("\nSearchItems(\"brand\"=[\"Nestle\"])");
        SearchCriteria brandCriteria = new SearchCriteria(Arrays.asList("Nestle"), null, null, null);
        List<InventoryItem> brandResults = searchService.searchItems(brandCriteria, new SortCriteria());
        brandResults.forEach(System.out::println);

        // Search by category
        System.out.println("\nSearchItems(\"category\"=[\"Milk\"])");
        SearchCriteria categoryCriteria = new SearchCriteria(null, Arrays.asList("Milk"), null, null);
        List<InventoryItem> categoryResults = searchService.searchItems(categoryCriteria, new SortCriteria());
        categoryResults.forEach(System.out::println);

        // Search by category with price descending
        System.out.println("\nSearchItems(\"category\"=[\"Milk\"], Order_By=[Price,desc])");
        SearchCriteria categoryCriteria2 = new SearchCriteria(null, Arrays.asList("Milk"), null, null);
        SortCriteria priceDescSort = new SortCriteria(
                SortCriteria.SortField.PRICE,
                SortCriteria.SortOrder.DESC
        );
        List<InventoryItem> categoryPriceResults = searchService.searchItems(categoryCriteria2, priceDescSort);
        categoryPriceResults.forEach(System.out::println);

        // Search by price range
        System.out.println("\nSearchItems(\"price\"=[70, 100])");
        SearchCriteria priceCriteria = new SearchCriteria(null, null, 70.0, 100.0);
        List<InventoryItem> priceResults = searchService.searchItems(priceCriteria, new SortCriteria());
        priceResults.forEach(System.out::println);

        // Search by multiple criteria
        System.out.println("\nSearchItems([\"category\"=[\"Milk\"], \"price\"=[70, 100]], Order_By=[Price,desc])");
        SearchCriteria multiCriteria = new SearchCriteria(null, Arrays.asList("Milk"), 70.0, 100.0);
        List<InventoryItem> multiResults = searchService.searchItems(multiCriteria, priceDescSort);
        multiResults.forEach(System.out::println);

        System.out.println("\nDemo completed.");
    }
}