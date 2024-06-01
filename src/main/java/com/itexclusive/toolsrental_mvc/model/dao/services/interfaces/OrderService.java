package com.itexclusive.toolsrental_mvc.model.dao.services.interfaces;


import com.itexclusive.toolsrental_mvc.model.entities.shop.Order;
import com.itexclusive.toolsrental_mvc.model.entities.shop.StockPosition;

public interface OrderService extends DAO<Order>{
    void pay(int id);
    void addPosition(Order order, StockPosition stockPosition, int amount);
}
