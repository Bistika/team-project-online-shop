package com.shop.ui;

import com.shop.exception.ShopException;
import com.shop.model.User;
import com.shop.service.UserService;
import com.shop.ui.handlers.InputPopUps;
import com.shop.ui.validator.PhoneNoValidator;

import static com.shop.ui.handlers.InputPopUps.CANCELLED;

/**
 * UserAccountUI class - connects user input to UserService class to manage account data.
 */

class UserAccountUI {

    private static final String VIEW_ACCOUNT = "1";
    private static final String MODIFY_ACCOUNT = "2";
    private static String dataToShow = "";
    private static final String CHANGE_USERNAME = "1";
    private static final String CHANGE_PASSWORD = "2";
    private static final String CHANGE_PHONENO = "3";
    private static String userInput = "";
    private static String result = "";

    private UserService userService = new UserService();
    private PhoneNoValidator phoneNoValidator = new PhoneNoValidator();

    void manageAccount(User user) throws ShopException {
        do {
            userInput = "";
            userInput = InputPopUps.input("USER ACCOUNT MENU:\n\n1 : VIEW ACCOUNT\n2 : MODIFY ACCOUNT\n\n" + dataToShow);
            switch (userInput) {
                case VIEW_ACCOUNT:
                    dataToShow = user.printCompleteUserData();
                    userInput = "";
                    break;
                case MODIFY_ACCOUNT:
                    modifyAccount(user);
                    dataToShow = user.printCompleteUserData();
                    userInput = "";
                    break;
                default:
                    dataToShow = "PLEASE ENTER A VALID OPTION.";
            }
        } while (!userInput.equals(CANCELLED));
    }

    private void modifyAccount(User user) throws ShopException {
        do {
            userInput = InputPopUps.input("MODIFY ACCOUNT:\n\n1 : CHANGE USER NAME\n2 : CHANGE PASSWORD\n3 : CHANGE PHONE NUMBER\n\n" + result + "\n\n" + dataToShow);
            dataToShow = "";
            switch (userInput) {
                case CHANGE_USERNAME:
                    result = changeUserName(user);
                    dataToShow = user.printCompleteUserData();
                    userInput = "";
                    break;
                case CHANGE_PASSWORD:
                    result = changePassword(user);
                    dataToShow = user.printCompleteUserData();
                    userInput = "";
                    break;
                case CHANGE_PHONENO:
                    result = changePhoneNo(user);
                    dataToShow = user.printCompleteUserData();
                    userInput = "";
                    break;
                default:
                    dataToShow = "PLEASE ENTER A VALID OPTION.";
            }
        } while (!userInput.equals(CANCELLED));
    }

    private String changeUserName(User user) throws ShopException {
        userInput = InputPopUps.input("ENTER NEW USER NAME:\n\n" + dataToShow);
        if (!userInput.equals(CANCELLED) && !userInput.equals(".")) {
            user.setUserName(userInput);
            userService.replaceUserData(user);
            return "USER NAME UPDATED.";
        }
        if (userInput.equals(".")) {
            return "PLEASE ENTER A VALID USER NAME.";
        } else {
            return "";
        }
    }

    private String changePassword(User user) throws ShopException {
        userInput = InputPopUps.input("ENTER NEW PASSWORD (CANNOT BE EMPTY):\n\n" + dataToShow);
        if (!userInput.equals(CANCELLED) && !userInput.equals(".")) {
            user.setPassword(userInput);
            userService.replaceUserData(user);
            return "PASSWORD UPDATED.";
        }
        if (userInput.equals(".")) {
            return "PLEASE ENTER A VALID PASSWORD.";
        } else {
            return "";
        }
    }

    private String changePhoneNo(User user) throws ShopException {
        userInput = InputPopUps.input("ENTER NEW ROMANIAN PHONE NUMBER:\n\n" + dataToShow);
        if (phoneNoValidator.isPhoneNoValid((userInput))) {
            userInput = phoneNoValidator.formatPhoneNo(userInput);
            user.setPhoneNo(userInput);
            userService.replaceUserData(user);
            return "PHONE NUMBER UPDATED.";
        }
        if (userInput.equals(".")) {
            return "PLEASE ENTER A VALID ROMANIAN PHONE NUMBER.";
        } else {
            return "";
        }
    }
}