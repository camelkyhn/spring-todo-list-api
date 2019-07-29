package com.camelkyhn.springtodolistapi.middleware.dtos.todolist;

import com.camelkyhn.springtodolistapi.middleware.bases.BaseFilterDto;

import java.util.Map;

public class TodoListFilterDto extends BaseFilterDto {
    private String name;

    private String assignedUsername;

    public TodoListFilterDto() {
    }

    public TodoListFilterDto(Boolean isAllData) {
        super(isAllData);
    }

    public TodoListFilterDto(Map<String, String[]> data) {
        super(data);
        setName(getStringParameter(data, "name"));
        setAssignedUsername(getStringParameter(data, "assignedUsername"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssignedUsername() {
        return assignedUsername;
    }

    public void setAssignedUsername(String assignedUsername) {
        this.assignedUsername = assignedUsername;
    }
}
