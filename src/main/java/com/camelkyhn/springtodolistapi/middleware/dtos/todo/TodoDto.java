package com.camelkyhn.springtodolistapi.middleware.dtos.todo;

import com.camelkyhn.springtodolistapi.middleware.bases.BaseDto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class TodoDto extends BaseDto {
    @NotEmpty
    @Length(max = 200, min = 3)
    private String name;

    @NotEmpty
    @Length(max = 500, min = 3)
    private String description;

    private Date deadline;

    private boolean completed;

    @NotNull
    private Long todoListId;

    private Long dependentTodoId;

    public TodoDto() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
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

    public void setDependentTodoId(Long dependedTodoId) {
        this.dependentTodoId = dependedTodoId;
    }
}