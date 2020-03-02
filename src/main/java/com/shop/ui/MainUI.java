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

    private static String WELCOME_GRAPHIC =
        "\n" +
        "\n" +
        "                                                            \n" +
        "   @@@@@@@   @@@    @@  @@@      @@@  @@@     @@  @@@@@@@@  \n" +
        "  @@@    @@  @@@@@  @@  @@@      @@@  @@@@@   @@  @@@       \n" +
        "  @@@    @@  @@@@@@ @@  @@@      @@@  @@@ @@  @@  @@@@@@    \n" +
        "  @@@    @@  @@@  @@@@  @@@      @@@  @@@  @@ @@  @@@       \n" +
        "   @@@@@@@   @@@   @@@  @@@@@@@  @@@  @@@   @@@@  @@@@@@@@  \n" +
        "                                                            \n" +
        "                                                            \n" +
        "    @@@@@@@@   @@@@     @@@     @@@@@@@@@     @@@@@@@@@@    \n" +
        "  @@@@     @@  @@@@     @@@   @@@@      @@@   @@@@     @@@  \n" +
        "  @@@@         @@@@     @@@  @@@@        @@@  @@@@     @@@  \n" +
        "    @@@@@@@    @@@@     @@@  @@@@        @@@  @@@@     @@@  \n" +
        "         @@@@  @@@@@@@@@@@@  @@@@        @@@  @@@@@@@@@@    \n" +
        "  @@     @@@@  @@@@     @@@   @@@@      @@@   @@@@          \n" +
        "   @@@@@@@@@   @@@@     @@@     @@@@@@@@@     @@@@          \n" +
        "                                                            \n" +
        "\n" +
        "\n";

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
                userInput = InputPopUps.input(
                    WELCOME_GRAPHIC +
                    "PLEASE CHOOSE AN OPTION BY ITS NUMBER:\n\n1 : LOG IN\n2 : SIGN UP\n3 : CHANGE THEME\n\n" +
                    dataToShow);
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
                        dataToShow = "PLEASE ENTER A VALID OPTION:";
                    }
                }
            }
        }
        while (!userInput.equals(CANCELLED));
    }

    private String changeTheme() {
        userInput = InputPopUps.input("CHOOSE ONE OF THE FOLLOWING THEMES:\n1 : WHITE\n2 : GRAY\n3 : BLACK\n");
        if (userInput.equals(WHITE_THEME)) {
            inputPopUps.setPaneBG(Color.white);
            inputPopUps.setPanelBG(Color.white);
            inputPopUps.setTxtAreaBG(Color.white);
            inputPopUps.setTxtAreaFG(Color.black);
            return "WHITE THEME SET.";
        }
        if (userInput.equals(GRAY_THEME)) {
            inputPopUps.setPaneBG(Color.black);
            inputPopUps.setPanelBG(Color.DARK_GRAY);
            inputPopUps.setTxtAreaBG(Color.DARK_GRAY);
            inputPopUps.setTxtAreaFG(Color.white);
            return "GRAY THEME SET.";
        }
        if (userInput.equals(DARK_THEME)) {
            inputPopUps.setPaneBG(Color.black);
            inputPopUps.setPanelBG(Color.black);
            inputPopUps.setTxtAreaBG(Color.black);
            inputPopUps.setTxtAreaFG(Color.white);
            return "BLACK THEME SET. MAY YOUR EYES NEVER BURN AGAIN :)";
        }
        else {
            return "OPTION NOT AVAILABLE.";
        }
    }
}