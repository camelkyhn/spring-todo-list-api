package com.camelkyhn.springtodolistapi.middleware.dtos.role;

import com.camelkyhn.springtodolistapi.middleware.bases.BaseDto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class RoleDto extends BaseDto {
    @Length(max = 20, min = 3)
    @NotEmpty
    private String name;

    public RoleDto() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}