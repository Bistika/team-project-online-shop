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
            userInput = InputPopUps.input(dataToShow + "YOUR CART'S CURRENT CONTENT:\n\n1 : EDIT CART\n2 : CREATE ORDER\n\n" + loadCart());
            dataToShow = "";
            if (!userInput.equals(CANCELLED)) {
                switch (userInput) {
                    case Edit_CART: {
                        editCart(loadCart());
                    }
                    break;
                    case CREATE_ORDER: {
                        cartService.createOrder();
                        dataToShow = "ORDER CREATED.";
                        break;
                    }
                    default: {
                        dataToShow = "PLEASE ENTER A VALID OPTION.";
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
            userInput = InputPopUps.input("1 : EDIT QUANTITY\n2 : DELETE PRODUCT\n\n" + shoppingCart);
            if (!userInput.equals(CANCELLED)) {
                switch (userInput) {
                    case EDIT_QUANTITY: {
                        String productId = InputPopUps.input("ID OF THE PRODUCT TO BE UPDATED:\n\n" + shoppingCart);
                        String newQuantity = InputPopUps.input("NEW DESIRED QUANTITY:");
                        cartService.editQuantity(parseInt(productId), parseInt(newQuantity));
                        shoppingCart = loadCart();
                        break;
                    }
                    case DELETE_PRODUCT: {
                        String productId = InputPopUps.input("ID OF THE PRODUCT TO BE DELETED:\n\n" + shoppingCart);
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
            stringBuilder.append(product.toString()).append(" ").append("QUANTITY: ").append(cartService.showQuantity(product).toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}