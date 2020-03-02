package com.shop.ui.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    public boolean isEmailValid(String email) {
        String patternString = ".+@.+?\\..+";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}