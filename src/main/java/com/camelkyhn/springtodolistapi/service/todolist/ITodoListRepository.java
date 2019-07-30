package com.camelkyhn.springtodolistapi.service.todolist;

import com.camelkyhn.springtodolistapi.middleware.entities.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITodoListRepository extends JpaRepository<TodoList, Long> {
}