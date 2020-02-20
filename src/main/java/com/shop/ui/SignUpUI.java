package com.shop.ui;

import com.shop.ui.ui_handlers.InputPopUps;
import com.shop.ui.ui_handlers.PrintUI;
import com.shop.exception.ShopException;
import com.shop.service.UserService;

/** SignUpUI class - creates new users by collecting user inputs. */

class SignUpUI {
    private static final String CANCEL = "NullPointerExceptionFound";
    private UserService userService = new UserService();

    //  SignUp UI starting point:
    void displaySignUp() throws ShopException {
        PrintUI.printBox("To sign up, please provide the following credentials:");
        String inputEmail = InputPopUps.input("Email:");
        String inputPassword = InputPopUps.input("New Password");
        if (!inputEmail.matches(CANCEL) & !inputPassword.matches(CANCEL)) {
            if (userService.signUp(inputPassword, inputEmail)) {
                PrintUI.printBox("User Exists, Please Login.");
            } else {
                PrintUI.printBox("Welcome! Sign Up successful, please proceed to LogIn");
            }
        }
        else {
            PrintUI.printBox("User canceled operation");
        }
    }
}