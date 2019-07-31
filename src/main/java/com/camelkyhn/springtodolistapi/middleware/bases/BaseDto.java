package com.camelkyhn.springtodolistapi.middleware.bases;

import com.camelkyhn.springtodolistapi.middleware.enums.Status;

import javax.validation.constraints.NotNull;

public abstract class BaseDto {

    @NotNull
    private Status status;

    public BaseDto() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
