package com.shop.service;

import com.shop.cache.ProductCache;
import com.shop.dao.ProductDAO;
import com.shop.exception.ShopTechnicalException;
import com.shop.model.Product;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductService {

    private ProductDAO productDAO = new ProductDAO();
    private ProductCache productCache = ProductCache.get();

    public void addProduct(String inputProdName, String inputProdCategory, String inputProdQuantity, String inputProductPrice) throws ShopTechnicalException {
        productDAO.createProduct(new Product(productDAO.getNextId(), inputProdName, inputProdCategory, inputProdQuantity, inputProductPrice));
        productCache.load();
    }
    public Set<String> getCategories () {
        return productCache.cache.values().stream()
                .map(Product::getCategory)
                .collect(Collectors.toSet());
    }
    public Set<String> getProductNamesForCategory (String Category) {
        return productCache.cache.values().stream()
                .filter(Product -> Product.getCategory().toLowerCase().matches( ".*"+ Category.toLowerCase() +".*"))
                .map(Product::getName)
                .collect(Collectors.toSet());
    }

    public List<Product> getProductsByCategoryAndName(String categoryName, String productName) {
        return productCache.cache.values().stream()
                .filter(product -> matchesCategoryAndName(categoryName, productName, product))
                .collect(Collectors.toList());
    }

    public Product getProductByID(Integer id) {
        return productCache.cache.get(id);
    }

    private boolean matchesCategoryAndName(String categoryName, String ProductName, Product product) {
        return product.getCategory().toLowerCase().matches(".*" + categoryName.toLowerCase() + ".*") && product.getName().toLowerCase().matches(".*" + ProductName.toLowerCase() + ".*");
    }
}