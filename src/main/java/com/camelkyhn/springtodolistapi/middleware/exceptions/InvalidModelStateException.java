package com.camelkyhn.springtodolistapi.middleware.exceptions;

import org.springframework.validation.ObjectError;

import java.util.Objects;

public class InvalidModelStateException extends Exception {
    public InvalidModelStateException(String model) {
        super("This " + model + " model is null/empty or not in expected format!");
    }

    public InvalidModelStateException(ObjectError error) {
        super("Field error in object: [" + error.getObjectName() + "], code: [" + Objects.requireNonNull(error.getCodes())[0] + "], message: [" + error.getDefaultMessage() + "]");
    }
}
