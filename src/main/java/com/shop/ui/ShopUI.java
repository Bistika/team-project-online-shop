package com.shop.ui;

import com.shop.exception.ShopException;
import com.shop.security.UserContext;
import com.shop.ui.handlers.InputPopUps;

import static com.shop.ui.handlers.InputPopUps.CANCELLED;

class ShopUI {

    private static final String OPTION_SHOP = "1";
    private static final String OPTION_ACCOUNT = "2";
    private static final String OPTION_LOGOUT = "0";

    private AdminShopUI adminShopUI = new AdminShopUI();
    private AdminManageAccountsUI adminManageAccountsUI = new AdminManageAccountsUI();
    private UserShopUI userShopUi = new UserShopUI();
    private UserAccountUI userAccountUI = new UserAccountUI();

    void start() throws ShopException {
        String userInput;
        String dataToShow = "";
        do {
            userInput = InputPopUps.input("WELCOME TO OUR ONLINE SHOP!\n" + dataToShow + "\n1 : SHOP\n2 : MANAGE ACCOUNT\n0 : LOG OUT");
            switch (userInput) {
                case OPTION_SHOP: {
                    if (UserContext.isAdminLogged()) {
                        adminShopUI.manageProducts();
                    } else {
                        userShopUi.browseProducts();
                    }
                    break;
                }
                case OPTION_ACCOUNT: {
                    if (UserContext.isAdminLogged()) {
                        adminManageAccountsUI.manageAccounts();
                    } else {
                        userAccountUI.manageAccount(UserContext.getLoggedUser());
                    }
                    break;
                }
                case OPTION_LOGOUT: {
                    UserContext.setLoggedUser(null);
                    break;
                }
                case CANCELLED: {
                    break;
                }
                default: {
                    dataToShow = "PLEASE ENTER A VALID OPTION.";
                }
            }
        } while (!userInput.equals(OPTION_LOGOUT));
    }
}
