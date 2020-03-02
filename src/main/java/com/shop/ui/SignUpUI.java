package com.shop.ui;

import com.shop.exception.ShopException;
import com.shop.service.UserService;
import com.shop.ui.handlers.InputPopUps;
import com.shop.ui.validator.EmailValidator;

import static com.shop.ui.handlers.InputPopUps.CANCELLED;

/**
 * SignUpUI class - creates new users by collecting user inputs.
 */

class SignUpUI {

    private EmailValidator emailValidator = new EmailValidator();
    private UserService userService = new UserService();

    String displaySignUp() throws ShopException {
        String dataToShow = "";
        String email;
        String password;
        do {
            email = InputPopUps.input(dataToShow + "PLEASE ENTER A VALID EMAIL ADDRESS:");
            password = InputPopUps.input("PLEASE ENTER A VALID PASSWORD (IT CANNOT BE EMPTY):");
            if (!email.matches(CANCELLED) && !password.matches(CANCELLED)) {
                if (emailValidator.isEmailValid(email) && !password.equals(".")) {
                    if (userService.doesUserExist(email)) {
                        return "USER ALREADY EXISTS. PLEASE LOG IN.";
                    } else {
                        userService.signUp(password, email);
                        break;
                    }
                    } else {
                    dataToShow = "YOUR EMAIL/PASSWORD IS INVALID. PLEASE TRY AGAIN. ";
                }
            }
        } while (!email.equals(CANCELLED) && !password.equals(CANCELLED));

        return "";
    }
}