package com.shop.cache;

import com.shop.dao.ProductDAO;
import com.shop.exception.ShopFileException;
import com.shop.model.Product;

import java.util.HashMap;

public class ProductCache {
    private ProductDAO productDAO = new ProductDAO();
    private final static ProductCache instance = new ProductCache();

    public HashMap<Integer, Product> cache = new HashMap<>();

    private ProductCache() {
    }

    public static ProductCache get() {
        return instance;
    }

    public void load() throws ShopFileException {
        for (Product product : productDAO.findAllProducts()) {
            cache.put(product.getId(), product);
        }
    }

}
