package com.camelkyhn.springtodolistapi.service.todolist;

import com.camelkyhn.springtodolistapi.middleware.entities.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ITodoListRepository extends JpaRepository<TodoList, Long>, JpaSpecificationExecutor<TodoList> {
}