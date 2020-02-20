package com.shop.service;

import com.shop.dao.ProductDAO;
import com.shop.exception.ShopFileException;
import com.shop.exception.ShopTechnicalException;
import com.shop.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class ProductService {

    public static void addProduct(String inputProdName, String inputProdCategory, String inputProdQuantity, String inputProductPrice) throws ShopTechnicalException {
        List<Integer> productIds = new ArrayList<>();
        for (Product product : ProductDAO.findAllProducts()) {
            productIds.add(product.getUniqueProdId());
        }
        ProductDAO.createProduct(new Product(inputProdName, inputProdCategory, inputProdQuantity, inputProductPrice, (Collections.max(productIds) + 1)).DBprint());
    }

    public List<Product> getProductsByCategoryAndName(String categoryName, String ProductName) throws ShopFileException {
        return ProductDAO.findAllProducts().stream()
                .filter(product -> matchesCategoryAndName(categoryName, ProductName, product))
                .collect(Collectors.toList());
    }

    private boolean matchesCategoryAndName(String categoryName, String ProductName, Product product) {
        return product.getCategory().matches(".*" + categoryName + ".*") && product.getProductName().matches(".*" + ProductName + ".*");
    }
}