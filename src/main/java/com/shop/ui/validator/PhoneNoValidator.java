package com.shop.ui.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNoValidator {

    public String formatPhoneNo(String phoneNo) {
        String formattedPhoneNo = phoneNo.replaceAll("[^0-9+]", "");
        formattedPhoneNo = formattedPhoneNo.replaceAll("\\+40", "0040");
        if (!formattedPhoneNo.matches("0040.+")) {
            formattedPhoneNo = "004" + formattedPhoneNo;
        }
        return formattedPhoneNo;
    }

    public boolean isPhoneNoValid(String phoneNo) {
        String formattedPhoneNo = formatPhoneNo(phoneNo);
        String patternString = "^0040\\d{9}$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(formattedPhoneNo);
        return matcher.matches();
    }
}