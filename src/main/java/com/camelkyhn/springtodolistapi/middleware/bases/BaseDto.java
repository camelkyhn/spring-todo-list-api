package com.camelkyhn.springtodolistapi.middleware.bases;

import com.camelkyhn.springtodolistapi.middleware.enums.Status;

public abstract class BaseDto {

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
