package com.camelkyhn.springtodolistapi.service.todo;

import com.camelkyhn.springtodolistapi.middleware.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ITodoRepository extends JpaRepository<Todo, Long>, JpaSpecificationExecutor<Todo> {
}