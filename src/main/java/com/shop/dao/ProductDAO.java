package com.shop.dao;

import com.shop.cache.ProductCache;
import com.shop.exception.ShopFileException;
import com.shop.exception.ShopTechnicalException;
import com.shop.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ProductDAO {
        private FileUtil<Product> productReader = new FileUtil<>();
        private FileEdit<Product> productEditor = new FileEdit<>();
        private FileUtil<String> sequenceReader = new FileUtil<>();

        public Integer getNextId() throws ShopFileException {
            List<Integer> sequence = new ArrayList<>();
            List<String[]> ids = new ArrayList<>();
            sequenceReader.readEntities("product_seq.txt", i-> String.valueOf(sequence.add((parseInt(i[0])))));
            return (Collections.max(sequence)+1);
        }

    public void createProduct(Product product) throws ShopTechnicalException {
        productEditor.write("products.txt", product);
    }

    public List<Product> findAllProducts() throws ShopFileException {
        return productReader.readEntities("products.txt", lines -> new Product(parseInt(lines[0]), lines[1], lines[2], lines[3], lines[4]));
    }

}