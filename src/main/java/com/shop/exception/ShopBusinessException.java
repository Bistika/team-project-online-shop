package com.shop.exception;

/** Business exception for the online shop. Deals with user credentials. */

public class ShopBusinessException extends ShopException {

//  No-argument exception constructor:
    public ShopBusinessException() {
    }

//  Exception constructor:
    public ShopBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}