package com.camelkyhn.springtodolistapi.service.todolist;

import com.camelkyhn.springtodolistapi.middleware.entities.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ITodoListRepository extends JpaRepository<TodoList, Long>, JpaSpecificationExecutor<TodoList> {
    @Override
    @Query("select tl from TodoList tl where tl.id = :id and not (tl.status = 2)")
    Optional<TodoList> findById(@Param("id") Long id);
}