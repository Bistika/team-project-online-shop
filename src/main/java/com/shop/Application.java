package com.shop;

import com.shop.cache.ProductCache;
import com.shop.exception.ShopException;
import com.shop.ui.MainUI;

/** Application class - the program entry point. */

public class Application {
    public static void main(String[] args) throws ShopException {
        ProductCache.get().load();

        MainUI mainUI = new MainUI();
        mainUI.start();
    }
}