package com.shop.model;


/** Product class - the "purpose" of the shop. */

public class Product implements Writable{

//  Product parameters:
    private int id;
    private String name;
    private String category;
    private String price;
    private String stock;

//  Product class constructor:
    public Product(int id, String name, String category, String price, String stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public String toString (){
        return "Name: " + name + ", Category: " + category + ", Price: " + price + ", ProductId: " + id;
    }


//  Product getters and setters:
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }
    public void setStock(String stock) {
        this.stock = stock;
    }
    public int getId() {
        return id;
    }

    @Override
    public String toDb() {
        return id + "|" + name + "|" + category + "|" + price + "|" + stock;
    }
}