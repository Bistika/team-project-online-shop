package com.shop.ui.validator;

public class IntCheck {
    public static boolean check (String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
