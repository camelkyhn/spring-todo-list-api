package com.camelkyhn.springtodolistapi.middleware.dtos.todo;

import com.camelkyhn.springtodolistapi.middleware.bases.BaseFilterDto;

import java.util.Map;

public class TodoFilterDto extends BaseFilterDto {
    private String name;

    private Boolean completed;

    private Boolean expired;

    private Long todoListId;

    private Long dependentTodoId;

    public TodoFilterDto() {
    }

    public TodoFilterDto(Boolean isAllData) {
        super(isAllData);
    }

    public TodoFilterDto(Map<String, String[]> data) {
        super(data);
        setName(getStringParameter(data, "name"));
        setCompleted(getBooleanParameter(data, "completed"));
        setExpired(getBooleanParameter(data, "expired"));
        setTodoListId(getLongParameter(data, "todoListId"));
        setDependentTodoId(getLongParameter(data, "dependentTodoId"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Long getTodoListId() {
        return todoListId;
    }

    public void setTodoListId(Long todoListId) {
        this.todoListId = todoListId;
    }

    public Long getDependentTodoId() {
        return dependentTodoId;
    }

    public void setDependentTodoId(Long dependentTodoId) {
        this.dependentTodoId = dependentTodoId;
    }
}
