package com.shop.ui;

import com.shop.ui.ui_handlers.InputPopUps;
import com.shop.ui.ui_handlers.PrintUI;
import com.shop.exception.ShopException;

import static com.shop.security.UserContext.isUserLogged;

/** MainUI class - handles the initial contact with the user. */

public class MainUI {

    private static final String OPTION_LOGIN = "1";
    private static final String OPTION_SiGN_UP = "2";
    private static final String OPTION_LEAVE = "0";
    private static final String CANCEL = "NullPointerExceptionFound";

    private SignUpUI signUpUI = new SignUpUI();
    private LoginUI loginUI = new LoginUI();
    private ShopUI shopUI = new ShopUI();

    //  Main UI starting point:
    public void start() throws ShopException {
        String userInput = null;
        do{
            if (!isUserLogged()) {
                do {
                    PrintUI.printBox("Login : 1", "Sign Up : 2", "Leave Shop: 0");
                    userInput = InputPopUps.input("Option:");
                    switch (userInput) {
                        case OPTION_LOGIN: {
                            loginUI.displayLogin();
                            break;
                        }
                        case OPTION_SiGN_UP: {
                            signUpUI.displaySignUp();
                            break;
                        }
                        case OPTION_LEAVE: {
                            PrintUI.printBox("Come back soon!");
                            break;
                        }
                        case CANCEL: {
                            PrintUI.printBox("User canceled operation.");
                            break;
                        }
                        default: {
                            PrintUI.printBox("Please choose a valid option:");
                        }
                    }
                } while (!userInput.equals(OPTION_LEAVE) & !isUserLogged());
            } else shopUI.start();
        } while (!userInput.equals(OPTION_LEAVE));

    }
}