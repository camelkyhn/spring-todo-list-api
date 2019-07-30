package com.camelkyhn.springtodolistapi.middleware.exceptions;

public class NotFoundException extends Exception {
    public NotFoundException(String model) {
        super("This " + model + " model is not found!");
    }
}