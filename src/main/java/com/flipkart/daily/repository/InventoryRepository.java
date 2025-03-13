package com.flipkart.daily.repository;

import com.flipkart.daily.exception.ItemNotFoundException;
import com.flipkart.daily.model.InventoryItem;
import com.flipkart.daily.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InventoryRepository {
    private final Map<String, InventoryItem> inventory = new LinkedHashMap<>(); // Changed to LinkedHashMap
    private final ItemRepository itemRepository;

    @Autowired
    public InventoryRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void addInventory(String brand, String category, int quantity) {
        Item item = itemRepository.getItem(brand, category);
        String key = generateKey(brand, category);

        if (inventory.containsKey(key)) {
            InventoryItem existingItem = inventory.get(key);
            existingItem.addQuantity(quantity);
        } else {
            inventory.put(key, new InventoryItem(item, quantity));
        }
    }

    public InventoryItem getInventoryItem(String brand, String category) {
        String key = generateKey(brand, category);
        InventoryItem inventoryItem = inventory.get(key);
        if (inventoryItem == null) {
            throw new ItemNotFoundException("Inventory not found for brand: " + brand + " and category: " + category);
        }
        return inventoryItem;
    }

    public List<InventoryItem> getAllInventoryItems() {
        return new ArrayList<>(inventory.values());
    }

    private String generateKey(String brand, String category) {
        return brand.toLowerCase() + ":" + category.toLowerCase();
    }
}