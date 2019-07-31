package com.camelkyhn.springtodolistapi.service.todo;

import com.camelkyhn.springtodolistapi.middleware.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ITodoRepository extends JpaRepository<Todo, Long>, JpaSpecificationExecutor<Todo> {
    @Override
    @Query("select t from Todo t where t.id = :id and not (t.status = 2)")
    Optional<Todo> findById(@Param("id") Long id);
}