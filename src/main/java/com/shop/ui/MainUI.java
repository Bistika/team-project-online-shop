package com.shop.ui;

import com.shop.exception.ShopException;
import com.shop.ui.handlers.InputPopUps;

import java.awt.*;

import static com.shop.security.UserContext.isUserLogged;
import static com.shop.ui.handlers.InputPopUps.CANCELLED;

/**
 * MainUI class - directs user to LoginUI, signUpUI or shopUI.
 */

public class MainUI {

    private static final String OPTION_LOGIN = "1";
    private static final String OPTION_SIGN_UP = "2";
    private static final String CHANGE_THEME = "3";
    private static final String WHITE_THEME = "1";
    private static final String GRAY_THEME = "2";
    private static final String DARK_THEME = "3";
    private static String userInput = "";

    private InputPopUps inputPopUps = InputPopUps.get();

    private SignUpUI signUpUI = new SignUpUI();
    private LoginUI loginUI = new LoginUI();
    private ShopUI shopUI = new ShopUI();

    public void start() throws ShopException {
        String dataToShow = "";
        do {
            if (isUserLogged()) {
                shopUI.start();
                dataToShow = "";
            } else {
                userInput = InputPopUps.input("Please choose an option by its number:\n\nLogin: 1\nSign Up: 2\nChange Theme : 3\n\n" + dataToShow);
                switch (userInput) {
                    case OPTION_LOGIN: {
                        loginUI.displayLogin();
                        dataToShow = "";
                        break;
                    }
                    case OPTION_SIGN_UP: {
                        dataToShow = signUpUI.displaySignUp();
                        break;
                    }
                    case CHANGE_THEME: {
                        dataToShow = changeTheme();
                        break;
                    }
                    case CANCELLED: {
                        break;
                    }
                    default: {
                        dataToShow = "Please enter a valid option:";
                    }
                }
            }
        }
        while (!userInput.equals(CANCELLED));
    }

    private String changeTheme() {
        userInput = InputPopUps.input("Choose one of the following themes:\nWhite Theme : 1\nGray Theme : 2\nDark Theme : 3\n");
        if (userInput.equals(WHITE_THEME)) {
            inputPopUps.setPaneBG(Color.white);
            inputPopUps.setPanelBG(Color.white);
            inputPopUps.setTxtAreaBG(Color.white);
            inputPopUps.setTxtAreaFG(Color.black);
            return "";
        }
        if (userInput.equals(GRAY_THEME)) {
            inputPopUps.setPaneBG(Color.black);
            inputPopUps.setPanelBG(Color.DARK_GRAY);
            inputPopUps.setTxtAreaBG(Color.DARK_GRAY);
            inputPopUps.setTxtAreaFG(Color.white);
            return "";
        }
        if (userInput.equals(DARK_THEME)) {
            inputPopUps.setPaneBG(Color.black);
            inputPopUps.setPanelBG(Color.black);
            inputPopUps.setTxtAreaBG(Color.black);
            inputPopUps.setTxtAreaFG(Color.white);
            return "May your eyes never burn again :)";
        }
        else {
            return "Option not available";
        }
    }
}