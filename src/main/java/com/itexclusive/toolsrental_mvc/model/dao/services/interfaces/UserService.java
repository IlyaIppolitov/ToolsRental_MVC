package com.itexclusive.toolsrental_mvc.model.dao.services.interfaces;


import com.itexclusive.toolsrental_mvc.model.entities.shop.DTO.OrderDTO;
import com.itexclusive.toolsrental_mvc.model.entities.shop.Order;
import com.itexclusive.toolsrental_mvc.model.security.User;
import com.itexclusive.toolsrental_mvc.model.security.dto.UserDataDTO;

import java.util.Optional;

public interface UserService extends DAO<User> {
    public Optional<User> findByEmail(String username);

    void updateUsername(int id, String username);
    void updatePassword(int id, String password);
    boolean passwordVerified(int id, String password);
    User update(UserDataDTO userData);
    OrderDTO getCurrentOrderDTO(User user);
    Order getCurrentOrder(User user);
}

