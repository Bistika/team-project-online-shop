package com.shop.exception;

/** Generic exception for the online shop. */

public class ShopException extends Exception {

//  No-argument exception constructor:
    public ShopException() {
    }

//  Exception constructor:
    public ShopException (String message, Throwable cause) {
        super(message, cause);
    }

}