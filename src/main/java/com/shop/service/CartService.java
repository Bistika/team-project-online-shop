package com.shop.service;

import com.shop.dao.OrderDAO;
import com.shop.exception.ShopTechnicalException;
import com.shop.model.Product;
import com.shop.security.UserContext;

import java.util.ArrayList;

import static com.shop.model.Cart.cart;

public class CartService {

    private ProductService productService = new ProductService();
    private OrderDAO orderDAO = new OrderDAO();
    public boolean isCartNull() {return cart.isEmpty();}

    public void addToCart (Product product, Integer quantity){
        cart.put(product,quantity);
    }
    public ArrayList<Product> showKeys(){
        return new ArrayList<>(cart.keySet());
    }
    public Integer showQuantity(Product product) {
        return cart.getOrDefault(product, 0);
    }
    public void editQuantity (Integer id, Integer quantity) {
        cart.replace(productService.getProductByID(id), quantity);
    }
    public void deleteProduct (Integer id) {
        cart.remove(productService.getProductByID(id));
    }

    public void createOrder () throws ShopTechnicalException {
        orderDAO.createOrder(cart.get(), UserContext.getLoggedUser());
        cart.clear();
    }
}
