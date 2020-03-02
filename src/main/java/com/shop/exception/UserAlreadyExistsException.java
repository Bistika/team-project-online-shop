package com.shop.exception;

/** Duplicate user exception. Deals with user credentials. */

public class UserAlreadyExistsException extends ShopBusinessException {

//  No-argument exception constructor:
    public UserAlreadyExistsException () {
    }

//  Exception constructor:
    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}