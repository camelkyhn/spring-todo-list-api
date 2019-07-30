package com.camelkyhn.springtodolistapi.middleware.exceptions;

public class CanNotBeCompletedException extends Exception {
    public CanNotBeCompletedException(String item) {
        super("You can not complete this " + item + ", until you have done the other dependent one!");
    }
}