package com.shop.service;

import com.shop.dao.ProductDAO;
import com.shop.exception.ShopFileException;
import com.shop.exception.ShopTechnicalException;
import com.shop.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ProductService {
    public static void addProduct(String inputProdName, String inputProdCategory, String inputProdQuantity, String inputProducPrice) throws ShopTechnicalException {
        List<Integer> productIds = new ArrayList<>();
        for (Product product: ProductDAO.findAllProducts()){
            productIds.add(Integer.parseInt((product.getUniqueProdId())));
        }
        String productData = inputProdName + "|" + inputProdCategory + "|" + inputProdQuantity + "|" + inputProducPrice + "|" + (Collections.max(productIds)+1);
        ProductDAO.createProduct(productData);
    }

    //    }
    public static void displayProductsByCategoryAndName (String categoryName, String ProductName) throws ShopFileException {
        for (Product product : ProductDAO.findAllProducts()
        ) {
            if (product.getCategory().matches(".*"+ categoryName + ".*") && product.getProductName().matches(".*"+ ProductName + ".*")) {
                System.out.println(product.toString());
            }
        }
    }
}