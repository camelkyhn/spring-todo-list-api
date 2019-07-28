package com.camelkyhn.springtodolistapi.middleware.entities;

import com.camelkyhn.springtodolistapi.middleware.bases.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TodoList extends BaseEntity {
    @NotEmpty
    @Length(max = 200, min = 3)
    private String name;

    @ManyToOne
    @JoinColumn(name = "ASSIGNED_USER_ID")
    @JsonManagedReference
    private User assignedUser;

    @OneToMany(mappedBy = "todoList")
    @JsonBackReference
    private List<Todo> todos = new ArrayList<>();

    public TodoList() {
        super();
    }

    public TodoList(String name, User assignedUser) {
        super();
        this.name = name;
        this.assignedUser = assignedUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}