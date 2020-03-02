package com.shop.model;

import java.util.HashMap;

public class Cart extends HashMap<Product, Integer> implements Writable {

    public static final Cart cart = new Cart();

    public Cart get() {
        return cart;
    }
    @Override
    public String toDb() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Product product : cart.keySet()) {
            stringBuilder.append(product.toDb()).append("|").append(cart.get(product)).append("\n");
        }
        return stringBuilder.toString();
    }
}