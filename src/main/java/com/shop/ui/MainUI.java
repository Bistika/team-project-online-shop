package com.shop.ui;

import com.shop.exception.ShopException;
import com.shop.security.UserContext;

import java.util.Scanner;

/** MainUI class - handles the initial contact with the user. */

public class MainUI {

    public static final String OPTION_LOGIN = "1";
    public static final String OPTION_LEAVE = "3";
    //  Initializing objects of every UI type defined so far:
    private SignUpUI signUpUI = new SignUpUI();
    private LoginUI loginUI = new LoginUI();

//  Main UI starting point:
    public void start() throws ShopException {

//      Giving user instructions and waiting for their input:
        System.out.println(
            "\n__________________________________________________" +
            "\n1. Login | 2. Sign Up | 3. Leave Shop             " +
            "\n__________________________________________________\n");
        String userInput = null;
        do {
            Scanner keyboardScanner = new Scanner(System.in);
            userInput = keyboardScanner.nextLine();
            switch (userInput) {
                case OPTION_LOGIN : {
                    if (loginUI.displayLogin()) {
                        if (UserContext.isAdminLogged()) {
                            new AdminShopUI().showManageProducts();
                        } else {
                            new ShopperUI().showProducts();
                        }
                    }
                }; break;

                case OPTION_LEAVE: {

                }

                default: System.out.println("\nInvalid input. Please type the action's number:");
            }
//            for (;;) {

//          Typing "1" sends user to login menu:
            if (userInput.equals(OPTION_LOGIN)) {
                loginUI.displayLogin();
//                break;
            }

//          Typing "2" sends user to sign up menu:
            if (userInput.equals("2")) {
                signUpUI.displaySignUp();
//                break;
            }

//          Typing "3" ends the program:
            if (userInput.equals("3")) {
                System.out.println(
                    "\n__________________________________________________" +
                    "\nCome back soon!                                   " +
                    "\n__________________________________________________\n");
//                break;
            }
            else {
                System.out.println("\nInvalid input. Please type the action's number:");
            }
        } while (!OPTION_LEAVE.equals(userInput))

    }
}