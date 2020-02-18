package com.shop.model;

import java.util.ArrayList;

/** Cart class - stores products that have been added to cart. */
/*  SHOULD PERSIST IN ONE OF 2 WAYS:
    - AS A PROPERTY OF THE USER ("userValues[4]", SOMEHOW)
    - AS A NEW FILE, LINKED TO THE USER, THAT ONLY HE/SHE AND ADMINS CAN ACCESS */

public class Cart {

//  Cart parameters:
    private int cartId;
    private ArrayList<Product> cartContent;

//  Cart class constructor:
    public Cart(int cartId, ArrayList<Product> cartContent) {
        this.cartId = cartId;
        this.cartContent = cartContent;
    }

//  Cart getters and setters:
    public int getCartId() {
        return cartId;
    }
    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public ArrayList<Product> getCartContent() {
        return cartContent;
    }
    public void setCartContent(ArrayList<Product> cartContent) {
        this.cartContent = cartContent;
    }
}