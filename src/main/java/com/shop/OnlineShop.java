package com.shop;

import com.shop.exception.ShopException;
import com.shop.ui.MainUI;

/** OnlineShop class - the program entry point. */

public class OnlineShop {

    public static void main (String[] args) throws ShopException {
        System.out.println(
            "\n__________________________________________________" +
            "\nWelcome to our online shop!                       " +
            "\nYou can navigate menus by typing their number.    " +
            "\n__________________________________________________\n");
        MainUI mainUI = new MainUI();
        mainUI.start();
    }
}