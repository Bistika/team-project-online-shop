package com.shop.ui;

import com.shop.security.UserContext;
import com.shop.ui.ui_handlers.InputPopUps;
import com.shop.ui.ui_handlers.PrintUI;
import com.shop.exception.ShopException;
import com.shop.exception.ShopWrongCredentialsException;
import com.shop.service.UserService;

/** LoginUI class - lets existing users login to online shop: */

class LoginUI {
    private static final String CANCEL = "NullPointerExceptionFound";
    //  Initializing a UserService object for interacting with user database:
    private UserService userService = new UserService();

    //  Login UI starting point:
    void displayLogin() throws ShopException {
        PrintUI.printBox("Please provide your Login Credentials: ");
        String email = InputPopUps.input("Email:");
        String password = InputPopUps.input("Password:");
        if (!email.equals(CANCEL) & !password.equals(CANCEL)) {
            try {
                userService.login(email, password);
                PrintUI.printBox("Login Successful");
            } catch (ShopWrongCredentialsException e) {
                PrintUI.printBox("Wrong credentials.");
            }
        } else {
            PrintUI.printBox("User canceled operation");
        }
    }
}