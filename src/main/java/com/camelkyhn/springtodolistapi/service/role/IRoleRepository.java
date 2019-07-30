package com.camelkyhn.springtodolistapi.service.role;

import com.camelkyhn.springtodolistapi.middleware.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IRoleRepository extends JpaSpecificationExecutor<Role>, JpaRepository<Role, Long> {
}