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
            userInput = InputPopUps.input("Shop Menu:\nFilter Products : 1\nView Cart : 2\nAdd to Cart : 3\n\n" + result);
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
                        result = "Your Shopping Cart is empty";
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
                    result = "Please choose a valid option";
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
            String categoryName = InputPopUps.input("To stop filtering products to view, press Cancel" +
                    "\nPlease filter products you want to check by the following Categories: \n\n" + categoryMenu);
            if (!categoryName.equals(CANCELLED)) {
                for (String name : productService.getProductNamesForCategory(categoryName)) {
                    productMenu.append(name).append("\n");
                }
                String productName = InputPopUps.input("To stop filtering products to view, press Cancel" +
                        "\nPlease filter products by name: \n\n" + productMenu);
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
            return "No products found";
        }
    }

    private String addToCart(String filteredProductList) {

        String productIdForCart = InputPopUps.input("ID of the product to be added: \n\n" + filteredProductList);
        String quantity = InputPopUps.input("Quantity Desired: \n");
        if (!productIdForCart.equals(CANCELLED) && !quantity.equals(CANCELLED) && IntCheck.check(productIdForCart) && IntCheck.check(quantity)) {
            cartService.addToCart(productService.getProductByID(parseInt(productIdForCart)), parseInt(quantity));
            return "Product added to Cart Successfully";
        } else {
            return "0 Products were added to Cart";
        }
    }
}

