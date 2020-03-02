package com.shop.ui;

import com.shop.exception.ShopException;
import com.shop.model.Product;
import com.shop.service.CartService;
import com.shop.service.ProductService;
import com.shop.ui.handlers.InputPopUps;
import com.shop.ui.validator.IntCheck;

import static com.shop.ui.handlers.InputPopUps.CANCELLED;
import static java.lang.Integer.parseInt;

public class UserShopUI {
    private ProductService productService = new ProductService();
    private CartService cartService = new CartService();
    private CartUI cartUI = new CartUI();

    private static final String FILTER_PRODUCTS = "1";
    private static final String VIEW_CART = "2";
    private static final String ADD_TO_CART = "3";

    void browseProducts() throws ShopException {
        String userInput = null;
        String result = "";

        do {
            userInput = InputPopUps.input("SHOP MENU:\n\n1 : FILTER PRODUCTS\n2 : VIEW CART\n3 : ADD TO CART\n\n" + result);
            result = "";
            switch (userInput) {
                case FILTER_PRODUCTS: {
                    result = showFilterProducts();
                    break;
                }
                case VIEW_CART: {
                    if (!cartService.isCartNull()) {
                        cartUI.manageCart();
                        result = "";
                        break;
                    } else {
                        result = "YOUR SHOPPING CART IS EMPTY.";
                        break;
                    }
                }
                case ADD_TO_CART: {
                    result = addToCart(result);
                    break;
                }
                case CANCELLED: {
                    break;
                }
                default: {
                    result = "PLEASE CHOOSE A VALID OPTION.";
                }

            }
        } while (!userInput.equals(CANCELLED));

    }

    private String showFilterProducts() {
        String userInput = "";
        StringBuilder categoryMenu = new StringBuilder();
        StringBuilder productMenu = new StringBuilder();
        StringBuilder filteredProducts = new StringBuilder();

        do {
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
                if (!productName.equals(CANCELLED)) {
                    for (Product product : productService.getProductsByCategoryAndName(categoryName, productName)) {
                        filteredProducts.append(product.toString()).append("\n");
                    }
                } else {
                    break;
                }
                userInput = "OK";
            } else {
                userInput = CANCELLED;
            }

        } while (!userInput.equals(CANCELLED));

        if (filteredProducts.length() > 0) {
            return filteredProducts.toString();
        } else {
            return "NO PRODUCTS FOUND.";
        }
    }

    private String addToCart(String filteredProductList) {

        String productIdForCart = InputPopUps.input("ID OF THE PRODUCT TO BE ADDED TO CART:\n\n" + filteredProductList);
        String quantity = InputPopUps.input("DESIRED QUANTITY:\n");
        if (!productIdForCart.equals(CANCELLED) && !quantity.equals(CANCELLED) && IntCheck.check(productIdForCart) && IntCheck.check(quantity)) {
            cartService.addToCart(productService.getProductByID(parseInt(productIdForCart)), parseInt(quantity));
            return "PRODUCT SUCCESSFULLY ADDED TO CART.";
        } else {
            return "NO PRODUCTS WERE ADDED TO CART.";
        }
    }
}

