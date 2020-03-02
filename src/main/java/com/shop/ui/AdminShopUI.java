package com.shop.ui;

import com.shop.exception.ShopException;
import com.shop.exception.ShopTechnicalException;
import com.shop.model.Product;
import com.shop.service.ProductService;
import com.shop.ui.handlers.InputPopUps;

import static com.shop.ui.handlers.InputPopUps.CANCELLED;

class AdminShopUI {

    private static final String CREATE_PRODUCT = "1";
    private static final String FILTER_PRODUCTS = "2";
    private static String dataToShow = "";

    private ProductService productService = new ProductService();

    void manageProducts() throws ShopException {
        String userInput;
        String operationResult = "";
        do {
            userInput = InputPopUps.input("ADMIN PRODUCT MANAGEMENT OPTIONS:" + dataToShow +"\n\n1 : ADD PRODUCT\n2 : FILTER PRODUCTS\n\n" + operationResult);
            switch (userInput) {
                case CREATE_PRODUCT:
                    operationResult = showAddProduct();
                    dataToShow = "";
                    break;
                case FILTER_PRODUCTS:
                    operationResult = showFilterProducts();
                    dataToShow = "";
                    break;
                case CANCELLED:
                    dataToShow = "";
                    operationResult = "";
                    break;
                default:
                    dataToShow = "PLEASE ENTER A VALID OPTION.";
                    operationResult = "";
            }
        } while (!userInput.equals(CANCELLED));
    }

    private String showFilterProducts() {
        String userInput = "";
        StringBuilder categoryMenu = new StringBuilder();
        StringBuilder productMenu = new StringBuilder();
        StringBuilder filteredProducts = new StringBuilder();

        do {
            dataToShow = "";
            categoryMenu.setLength(0);
            productMenu.setLength(0);

            for (String category : productService.getCategories()) {
                categoryMenu.append(category).append("\n");
            }
            String categoryName = InputPopUps.input("TO FIND PRODUCTS, TYPE A CATEGORY FROM THE LIST BELOW." +
                    "\nTO STOP FILTERING AND VIEW RESULTS, PRESS ESC.\n\n" + categoryMenu);
            if (!categoryName.equals(CANCELLED)) {
                for (String name : productService.getProductNamesForCategory(categoryName)) {
                    productMenu.append(name).append("\n");
                }
                String productName = InputPopUps.input("TO CONTINUE, TYPE A PRODUCT NAME FROM THE LIST BELOW." +
                        "\nTO STOP FILTERING AND VIEW RESULTS, PRESS ESC.\n\n" + productMenu);

                for (Product product : productService.getProductsByCategoryAndName(categoryName, productName)) {
                    filteredProducts.append(product.toString()).append("\n");
                }
                break;
            } else {
                userInput = CANCELLED;
            }

        }while (!userInput.equals(CANCELLED));

        if (filteredProducts.length()>0) {
            return filteredProducts.toString();
        } else{
           return "NO PRODUCTS FOUND.";
        }
    }

    private String showAddProduct() throws ShopTechnicalException {
        String result = "";
        String inputProdName = InputPopUps.input("ENTER NEW PRODUCT'S NAME:");

        String inputProdCategory = InputPopUps.input("ENTER NEW PRODUCT'S CATEGORY:");

        String inputProdQuantity = InputPopUps.input("ENTER NEW PRODUCT'S QUANTITY:");

        String inputProdPrice = InputPopUps.input("ENTER NEW PRODUCT'S PRICE:");

        if (!inputProdCategory.equals(CANCELLED) && !inputProdName.equals(CANCELLED) && !inputProdPrice.equals(CANCELLED) && !inputProdQuantity.equals(CANCELLED)) {
            productService.addProduct(inputProdName, inputProdCategory, inputProdQuantity, inputProdPrice);
            result = "PRODUCT SUCCESSFULLY ADDED.";
        } else {
            result = "USER CANCELLED OPERATION.";
        }
        return result;
    }
}