package com.flipkart.daily.model;

import java.util.Objects;

public class Item {

    private String brand;
    private String category;
    private double price;

    public Item() {
    }

    public Item(String brand, String category, double price) {
        this.brand = brand;
        this.category = category;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(brand, item.brand) && Objects.equals(category, item.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, category);
    }

    @Override
    public String toString() {
        return "Item{" +
                "brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
