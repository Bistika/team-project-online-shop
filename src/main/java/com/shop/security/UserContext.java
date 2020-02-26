package com.shop.security;

import com.shop.model.User;

public class UserContext {

    private static User loggedUser;
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_SHOPPER = "Shopper";


    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(User loggedUser) {
        UserContext.loggedUser = loggedUser;
    }

    public static boolean isUserLogged() {
        return loggedUser != null;
    }

    public static boolean isAdminLogged() {
        return ROLE_ADMIN.equals(loggedUser.getRole());
    }
}
