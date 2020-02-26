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
            userInput = InputPopUps.input("User account menu:\n\nView account: 1\nModify account: 2\n\n" + dataToShow);
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
                    dataToShow = "Please enter a valid option:";
            }
        } while (!userInput.equals(CANCELLED));
    }

    private void modifyAccount(User user) throws ShopException {
        do {
            userInput = InputPopUps.input("Modify account menu:\n\nChange username: 1\nChange password: 2\nChange phone number: 3\n\n" + result + "\n\n" + dataToShow);
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
                    dataToShow = "Please enter a valid option:";
            }
        } while (!userInput.equals(CANCELLED));
    }

    private String changeUserName(User user) throws ShopException {
        userInput = InputPopUps.input("Enter new username:\n\n" + dataToShow);
        if (!userInput.equals(CANCELLED) && !userInput.equals(".")) {
            user.setUserName(userInput);
            userService.replaceUserData(user);
            return "Username Updated";
        }
        if (userInput.equals(".")) {
            return "Please insert a valid username";
        } else {
            return "";
        }
    }

    private String changePassword(User user) throws ShopException {
        userInput = InputPopUps.input("Enter new password:\n\n" + dataToShow);
        if (!userInput.equals(CANCELLED) && !userInput.equals(".")) {
            user.setPassword(userInput);
            userService.replaceUserData(user);
            return "Password Updated";
        }
        if (userInput.equals(".")) {
            return "Please insert a valid Password";
        } else {
            return "";
        }
    }

    private String changePhoneNo(User user) throws ShopException {
        userInput = InputPopUps.input("Enter new Romanian phone number:\n\n" + dataToShow);
        if (phoneNoValidator.isPhoneNoValid((userInput))) {
            userInput = phoneNoValidator.formatPhoneNo(userInput);
            user.setPhoneNo(userInput);
            userService.replaceUserData(user);
            return "Phone Number Updated";
        }
        if (userInput.equals(".")) {
            return "Please insert a valid Password";
        } else {
            return "";
        }
    }
}