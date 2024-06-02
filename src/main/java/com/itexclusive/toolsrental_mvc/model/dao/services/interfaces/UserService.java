package com.itexclusive.toolsrental_mvc.model.dao.services.interfaces;


import com.itexclusive.toolsrental_mvc.model.security.User;

import java.util.Optional;

public interface UserService extends DAO<User> {
    public Optional<User> findByUsername(String username);

    void updateUsername(int id, String username);
    void updatePassword(int id, String password);

}

