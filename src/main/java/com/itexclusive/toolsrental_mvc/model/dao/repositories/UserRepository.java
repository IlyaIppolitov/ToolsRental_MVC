package com.itexclusive.toolsrental_mvc.model.dao.repositories;

import com.itexclusive.toolsrental.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
