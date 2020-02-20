package com.shop.ui;

import com.shop.ui.ui_handlers.InputPopUps;
import com.shop.ui.ui_handlers.PrintUI;
import com.shop.exception.ShopException;
import com.shop.model.Product;
import com.shop.service.ProductService;

public class AdminShopUI {
    private ProductService productService = new ProductService();
    private static final String CANCEL = "NullPointerExceptionFound";

    void manageProducts() throws ShopException {
        String userInput = null;
        final String EXIT_MENU = "0";
        do {
            PrintUI.printBox("Admin Menu: ", "Create new products : 1", "Filter Products : 2", "Exit : 0");
            userInput = InputPopUps.input("Option: ");
            final String CREATE_PRODUCT = "1";
            final String FILTER_PRODUCTS = "2";
            switch (userInput) {
                case CREATE_PRODUCT: {
                    PrintUI.printBox("Please insert new product name: ");
                    String inputProdName = InputPopUps.input("Product Name: ");
                    PrintUI.printBox("Please insert new product category: ");
                    String inputProdCategory = InputPopUps.input("Product Category: ");
                    PrintUI.printBox("Please insert new product quantity: ");
                    String inputProdQuantity = InputPopUps.input("Product Quantity: ");
                    PrintUI.printBox("Please insert new product price: ");
                    String inputProdPrice = InputPopUps.input("Product Price: ");
                    ProductService.addProduct(inputProdName, inputProdCategory, inputProdQuantity, inputProdPrice);
                    PrintUI.printBox("Product Created.");
                    break;
                }
                case FILTER_PRODUCTS: {
                        PrintUI.printBox("Please insert part of the product category and name as requested (blank accepted).");
                        String categoryName = InputPopUps.input("Category: ");
                        String productName = InputPopUps.input("Product Name: ");
                        for (Product product : productService.getProductsByCategoryAndName(categoryName, productName)) {
                            {
                                PrintUI.printBox(product.toString());
                            }
                        }
                    }
                    break;
                case CANCEL: {
                    PrintUI.printBox("User canceled operation.");
                    break;
                }
                case EXIT_MENU: {
                    break;
                }
                default: {
                    PrintUI.printBox("Please choose a valid option");
                }

            }
        } while (!userInput.equals(EXIT_MENU));

    }
}