package com.shop.exception;

/** File exception. Deals with files. */

public class ShopFileException extends ShopTechnicalException {

//  No-argument exception constructor:
    public ShopFileException() {
    }

//  Exception constructor:
    public ShopFileException(String message, Throwable cause) {
        super(message, cause);
    }

}