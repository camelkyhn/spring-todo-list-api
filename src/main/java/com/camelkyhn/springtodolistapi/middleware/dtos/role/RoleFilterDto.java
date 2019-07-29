package com.camelkyhn.springtodolistapi.middleware.dtos.role;

import com.camelkyhn.springtodolistapi.middleware.bases.BaseFilterDto;

import java.util.Map;

public class RoleFilterDto extends BaseFilterDto {
    private String name;

    public RoleFilterDto() {
        super();
    }

    public RoleFilterDto(Boolean isAllData) {
        super(isAllData);
    }

    public RoleFilterDto(Map<String, String[]> data) {
        super(data);
        setName(getStringParameter(data, "name"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
