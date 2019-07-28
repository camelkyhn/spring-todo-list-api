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
import java.util.Date;
import java.util.List;

@Entity
public class Todo extends BaseEntity {
    @NotEmpty
    @Length(max = 200, min = 3)
    private String name;

    @NotEmpty
    @Length(max = 500, min = 3)
    private String description;

    private Date deadline;

    private boolean completed;

    @ManyToOne(optional = false)
    @JoinColumn(name = "TODO_LIST_ID")
    @JsonManagedReference
    private TodoList todoList;

    @ManyToOne
    @JoinColumn(name = "DEPENDENT_TODO_ID")
    @JsonManagedReference
    private Todo dependentTodo;

    @OneToMany(mappedBy = "dependentTodo")
    @JsonBackReference
    private List<Todo> relatedTodos = new ArrayList<>();

    public Todo() {
        super();
    }

    public Todo(String name, String description, Date deadline, boolean completed, TodoList todoList, Todo dependentTodo) {
        super();
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.completed = completed;
        this.todoList = todoList;
        this.dependentTodo = dependentTodo;
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

    public TodoList getTodoList() {
        return todoList;
    }

    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }

    public Todo getDependentTodo() {
        return dependentTodo;
    }

    public void setDependentTodo(Todo dependentTodo) {
        this.dependentTodo = dependentTodo;
    }

    public List<Todo> getRelatedTodos() {
        return relatedTodos;
    }

    public void setRelatedTodos(List<Todo> relatedTodos) {
        this.relatedTodos = relatedTodos;
    }
}