package com.itexclusive.toolsrental_mvc.model.dao.repositories;

import com.itexclusive.toolsrental_mvc.model.entities.shop.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}