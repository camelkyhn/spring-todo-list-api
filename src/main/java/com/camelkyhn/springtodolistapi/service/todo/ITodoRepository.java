package com.camelkyhn.springtodolistapi.service.todo;

import com.camelkyhn.springtodolistapi.middleware.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITodoRepository extends JpaRepository<Todo, Long> {
}