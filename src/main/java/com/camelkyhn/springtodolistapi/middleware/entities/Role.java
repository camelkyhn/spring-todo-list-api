package com.camelkyhn.springtodolistapi.middleware.entities;

import com.camelkyhn.springtodolistapi.middleware.bases.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Role extends BaseEntity {
    @Length(max = 20, min = 3)
    @NotEmpty
    private String name;

    public Role() {
        super();
    }

    public Role(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}