package com.camelkyhn.springtodolistapi.middleware.entities;

import com.camelkyhn.springtodolistapi.middleware.bases.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends BaseEntity {
    @NotEmpty
    @Length(max = 50, min = 3)
    private String firstName;

    @NotEmpty
    @Length(max = 50, min = 3)
    private String lastName;

    @NotEmpty
    @Length(max = 50, min = 3)
    @Email
    private String username;

    @NotEmpty
    @Length(max = 100, min = 6)
    private String password;

    @OneToMany(mappedBy = "assignedUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<TodoList> todoLists = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Role> roles = new ArrayList<>();

    public User() {
        super();
    }

    public User(String firstName, String lastName, String username, String password) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TodoList> getTodoLists() {
        return todoLists;
    }

    public void setTodoLists(List<TodoList> todos) {
        this.todoLists = todos;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}