package com.shop.ui;

import com.shop.datahandlers.formatter.PhNoFormat;
import com.shop.exception.ShopException;
import com.shop.exception.ShopTechnicalException;
import com.shop.service.UserService;
import java.util.Scanner;

/** SignUpUI class - creates new users by collecting user inputs. */

public class SignUpUI {

//  Initializing a UserService object for interacting with user database:
    private UserService userService = new UserService();

//  SignUp UI starting point:
    void displaySignUp() throws ShopException {

//      Wrapping method in a parameter-less for-loop, to be able close sign up at any step:
        for (;;) {

//          Getting user's input:
            Scanner keyboardScanner = new Scanner(System.in);

//      Asking for desired username:
            System.out.println("\nEnter desired username (or type \"0\" to cancel):");
            String userName;
//      If input is empty, the program keeps asking for a valid response:
            while ((userName = keyboardScanner.nextLine()).isEmpty()) {
                System.out.println("\nEnter desired username (or type \"0\" to cancel):");
            }
//      Typing "0" cancels the sign up:
            if (userName.matches("0")) {
                MainUI mainUi = new MainUI();
                mainUi.start();
                break;
            }

//      Asking for desired password:
            System.out.println("Enter desired password (or type \"0\" to cancel):");
            String password;
//      If input is empty, the program keeps asking for a valid response:
            while ((password = keyboardScanner.nextLine()).isEmpty()) {
                System.out.println("Enter desired password (or type \"0\" to cancel):");
            }
//      Typing "0" cancels the sign up:
            if (password.matches("0")) {
                MainUI mainUi = new MainUI();
                mainUi.start();
                break;
            }

//      Asking for an email:
            System.out.println("Enter your email address (or type \"0\" to cancel):");
            String email;
//      If input is empty, the program keeps asking for a valid response:
            while ((email = keyboardScanner.nextLine()).isEmpty()) {
                System.out.println("Enter your email address (or type \"0\" to cancel):");
            }
//      Typing "0" cancels the sign up:
            if (email.matches("0")) {
                MainUI mainUi = new MainUI();
                mainUi.start();
                break;
            }
/**     THE EMAIL VALIDATOR MUST BE CALLED HERE, FOR EXAMPLE VIA AN ELSE BLOCK */

//      Asking for a phone number:
            System.out.println("Enter your phone number (or type \"0\" to cancel):");
            String phoneNo;
//      If input is empty, the program keeps asking for a valid response:
            while ((phoneNo = keyboardScanner.nextLine()).isEmpty()) {
                System.out.println("Enter your phone number (or type \"0\" to cancel):");
            }
//      Typing "0" cancels the sign up:
            if (phoneNo.matches("0")) {
                MainUI mainUi = new MainUI();
                mainUi.start();
                break;
            }
/**     THE PHONE NUMBER VALIDATOR MUST BE CALLED HERE, FOR EXAMPLE VIA AN ELSE BLOCK */
            phoneNo = PhNoFormat.format(phoneNo);
//      Trying to add the given data to the user database:
            try {
//          If sign up failed because email is already used:
                while (!userService.signUp(userName, password, email, phoneNo)) {
//              Asking for new email:
                    System.out.println("Enter a different email address (or type \"0\" to cancel):");
//              If input is empty, the program keeps asking for a valid response:
                    while ((email = keyboardScanner.nextLine()).isEmpty()) {
                        System.out.println("Enter a different email address (or type \"0\" to cancel):");
                    }
//              Typing "0" cancels the sign up:
                    if (email.matches("0")) {
                        MainUI mainUi = new MainUI();
                        mainUi.start();
                        break;
                    }
                    /** THE EMAIL VALIDATOR MUST BE CALLED HERE, FOR EXAMPLE VIA AN ELSE BLOCK */
                    break;
                }

//      Managing possible exception:
            } catch (ShopTechnicalException e) {
                e.printStackTrace();
            }
//      For-loop is broken to close sign up:
        break;
        }

    }
}