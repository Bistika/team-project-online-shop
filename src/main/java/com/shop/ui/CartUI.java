package com.shop.ui;

import com.shop.exception.ShopTechnicalException;
import com.shop.model.Product;
import com.shop.service.CartService;
import com.shop.ui.handlers.InputPopUps;

import static com.shop.ui.handlers.InputPopUps.CANCELLED;
import static java.lang.Integer.parseInt;

class CartUI {
    private CartService cartService = new CartService();

    private static final String Edit_CART = "1";
    private static final String CREATE_ORDER = "2";

    void manageCart() throws ShopTechnicalException {
        String userInput;
        String dataToShow = "";
        do {
            userInput = InputPopUps.input(dataToShow + "\n\nEdit Cart : 1\nCreate Order : 2\n\n" + loadCart());
            dataToShow = "";
            if (!userInput.equals(CANCELLED)) {
                switch (userInput) {
                    case Edit_CART: {
                        editCart(loadCart());
                    }
                    break;
                    case CREATE_ORDER: {
                        cartService.createOrder();
                        dataToShow = "Order created";
                        break;
                    }
                    default: {
                        dataToShow = "Please insert valid option";
                    }
                }
            }
        } while (!userInput.equals(CANCELLED) && !cartService.isCartNull());
    }

    private void editCart(String shoppingCart) {
        String userInput;
        final String EDIT_QUANTITY = "1";
        final String DELETE_PRODUCT = "2";
        do {
            userInput = InputPopUps.input("Edit Quantity : 1\nDelete Product : 2\n\n" + shoppingCart);
            if (!userInput.equals(CANCELLED)) {
                switch (userInput) {
                    case EDIT_QUANTITY: {
                        String productId = InputPopUps.input("Product to be modified:\n\n" + shoppingCart);
                        String newQuantity = InputPopUps.input("Quantity Desired:");
                        cartService.editQuantity(parseInt(productId), parseInt(newQuantity));
                        shoppingCart = loadCart();
                        break;
                    }
                    case DELETE_PRODUCT: {
                        String productId = InputPopUps.input("Product to be deleted:\n\n" + shoppingCart);
                        cartService.deleteProduct(parseInt(productId));
                        shoppingCart = loadCart();
                        break;
                    }
                }
            }
        } while (!cartService.isCartNull() && !userInput.equals(CANCELLED));
    }

    private String loadCart() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.setLength(0);
        for (Product product : cartService.showKeys()) {
            stringBuilder.append(product.toString()).append(" ").append("Quantity: ").append(cartService.showQuantity(product).toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}