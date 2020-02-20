package com.shop.dao;

import com.shop.exception.ShopFileException;
import com.shop.exception.ShopTechnicalException;
import com.shop.model.Product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class ProductDAO {
    public static void createProduct(String productData) throws ShopTechnicalException {
        FileEdit.write("products.txt", productData);
    }

    public static List <Product> findAllProducts() throws ShopFileException {
        List<String> ProductData = new ArrayList<>();
        try {
            File productFile = new File("./src/main/resources/products.txt");
            Scanner productScanner = new Scanner(productFile);
            while (productScanner.hasNextLine()) {
                    String line = productScanner.nextLine();
                if (line.length() != 0) {
                    ProductData.add(line);
                }
            }

        } catch (
                IOException e) {
            throw new ShopFileException("Products File not found", e);
        }
        List<Product> ProdList = new ArrayList<>();
        for (String prod : ProductData) {
            String[] prodValues = prod.split("\\|");
            ProdList.add(new Product(prodValues[0], prodValues[1], prodValues[2], prodValues[3], parseInt(prodValues[4])));
        }
        return ProdList;
    }
}