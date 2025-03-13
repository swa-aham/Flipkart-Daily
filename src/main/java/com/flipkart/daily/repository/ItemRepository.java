package com.flipkart.daily.repository;

import com.flipkart.daily.exception.ItemNotFoundException;
import com.flipkart.daily.model.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    // Using a Map with composite key (brand+category) as key and Item as value for quick access
    private final Map<String, Item> items = new LinkedHashMap<>();

    public void addItem(Item item) {
        String key = generateKey(item.getBrand(), item.getCategory());
        items.put(key, item);
    }

    public Item getItem(String brand, String category) {
        String key = generateKey(brand, category);
        Item item = items.get(key);
        if (item == null) {
            throw new ItemNotFoundException("Item not found for brand: " + brand + " and category: " + category);
        }
        return item;
    }

    public boolean itemExists(String brand, String category) {
        String key = generateKey(brand, category);
        return items.containsKey(key);
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(items.values());
    }

    private String generateKey(String brand, String category) {
        return brand.toLowerCase() + ":" + category.toLowerCase();
    }
}