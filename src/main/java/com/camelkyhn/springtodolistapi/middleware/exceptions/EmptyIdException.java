package com.camelkyhn.springtodolistapi.middleware.exceptions;

public class EmptyIdException extends Exception {
    public EmptyIdException(String reference) {
        super(reference + ": Id has to be greater than zero!");
    }
}