package com.flipkart.daily.service;

import com.flipkart.daily.exception.InvalidRequestException;
import com.flipkart.daily.model.InventoryItem;
import com.flipkart.daily.model.Item;
import com.flipkart.daily.repository.InventoryRepository;
import com.flipkart.daily.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    private final ItemRepository itemRepository;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(ItemRepository itemRepository, InventoryRepository inventoryRepository) {
        this.itemRepository = itemRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public void addItem(String brand, String category, double price) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new InvalidRequestException("Brand cannot be empty");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new InvalidRequestException("Category cannot be empty");
        }
        if (price < 0) {
            throw new InvalidRequestException("Price cannot be negative");
        }

        Item item = new Item(brand, category, price);
        itemRepository.addItem(item);
    }

    public void addInventory(String brand, String category, int quantity) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new InvalidRequestException("Brand cannot be empty");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new InvalidRequestException("Category cannot be empty");
        }
        if (quantity <= 0) {
            throw new InvalidRequestException("Quantity must be positive");
        }

        if (!itemRepository.itemExists(brand, category)) {
            throw new InvalidRequestException("Item does not exist for brand: " + brand + " and category: " + category);
        }

        inventoryRepository.addInventory(brand, category, quantity);
    }

    public List<InventoryItem> getAllInventoryItems() {
        return inventoryRepository.getAllInventoryItems();
    }

    public InventoryItem getInventoryItem(String brand, String category) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new InvalidRequestException("Brand cannot be empty");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new InvalidRequestException("Category cannot be empty");
        }

        return inventoryRepository.getInventoryItem(brand, category);
    }

    public void updateInventory(String brand, String category, int newQuantity) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new InvalidRequestException("Brand cannot be empty");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new InvalidRequestException("Category cannot be empty");
        }
        if (newQuantity < 0) {
            throw new InvalidRequestException("Quantity cannot be negative");
        }

        InventoryItem item = inventoryRepository.getInventoryItem(brand, category);
        item.setQuantity(newQuantity);
    }

    public void removeInventory(String brand, String category, int quantity) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new InvalidRequestException("Brand cannot be empty");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new InvalidRequestException("Category cannot be empty");
        }
        if (quantity <= 0) {
            throw new InvalidRequestException("Quantity must be positive");
        }

        InventoryItem item = inventoryRepository.getInventoryItem(brand, category);
        if (item.getQuantity() < quantity) {
            throw new InvalidRequestException("Not enough inventory available");
        }

        item.setQuantity(item.getQuantity() - quantity);
    }
}