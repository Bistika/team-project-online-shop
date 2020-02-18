package com.shop.exception;

/** Wrong credentials exception. Deals with user credentials. */

public class ShopWrongCredentialsException extends ShopBusinessException {

//  No-argument exception constructor:
    public ShopWrongCredentialsException () {
    }

//  Exception constructor:
    public ShopWrongCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

}