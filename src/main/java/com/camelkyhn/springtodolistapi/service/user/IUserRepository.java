package com.camelkyhn.springtodolistapi.service.user;

import com.camelkyhn.springtodolistapi.middleware.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends JpaRepository<User, Long>
{
    @Query("select u from User u where u.username = :username and not (u.status = 2)")
    User findByUsername(@Param("username") String username);
}