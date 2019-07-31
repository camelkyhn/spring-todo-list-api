package com.camelkyhn.springtodolistapi.service.role;

import com.camelkyhn.springtodolistapi.middleware.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IRoleRepository extends JpaSpecificationExecutor<Role>, JpaRepository<Role, Long> {
    @Override
    @Query("select r from Role r where r.id = :id and not (r.status = 2)")
    Optional<Role> findById(@Param("id") Long id);
}