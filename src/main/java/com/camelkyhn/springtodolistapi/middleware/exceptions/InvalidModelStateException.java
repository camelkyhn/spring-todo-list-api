package com.camelkyhn.springtodolistapi.middleware.exceptions;

public class InvalidModelStateException extends Exception {
    public InvalidModelStateException(String model) {
        super("This " + model + " model is null/empty or not in expected format!");
    }
}
