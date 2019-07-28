package com.camelkyhn.springtodolistapi.middleware.bases;

import com.camelkyhn.springtodolistapi.middleware.enums.Status;

public abstract class BaseDto {

    private Long id;

    private Status status;

    public BaseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
