package com.shop.exception;

/** Technical exception for the online shop. Deals with technical aspects. */

public class ShopTechnicalException extends ShopException {

//  No-argument exception constructor:
    public ShopTechnicalException() {
    }

//  Exception constructor:
    public ShopTechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

}