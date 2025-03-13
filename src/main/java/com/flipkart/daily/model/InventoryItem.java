package com.flipkart.daily.model;

public class InventoryItem {

    private Item item;
    private int quantity;

    public InventoryItem() {
    }

    public InventoryItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(int additionalQuantity) {
        this.quantity += additionalQuantity;
    }

    @Override
    public String toString() {
        return item.getBrand() + ", " + item.getCategory() + ", " + quantity;
    }
}
