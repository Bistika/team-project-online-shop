package com.shop;

import com.shop.exception.ShopException;
import com.shop.ui.AdminShopUI;
import com.shop.ui.MainUI;

/** OnlineShop class - the program entry point. */

public class OnlineShop {
    public static void main(String[] args) throws ShopException {
            MainUI mainUI = new MainUI();
            mainUI.start();
            Runtime.getRuntime().exit(0);
    }
}