package com.camelkyhn.springtodolistapi.middleware.dtos.todolist;

import com.camelkyhn.springtodolistapi.middleware.bases.BaseDto;
import org.hibernate.validator.constraints.Length;

public class TodoListDto extends BaseDto {
    @Length(max = 50, min = 3)
    private String name;

    private Long assignedUserId;

    public TodoListDto() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(Long assignedUserId) {
        this.assignedUserId = assignedUserId;
    }
}