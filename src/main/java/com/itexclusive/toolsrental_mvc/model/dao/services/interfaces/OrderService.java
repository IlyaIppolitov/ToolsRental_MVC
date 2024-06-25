package com.itexclusive.toolsrental_mvc.model.dao.services.interfaces;


import com.itexclusive.toolsrental_mvc.model.entities.shop.DTO.ShortOrdersDTO;
import com.itexclusive.toolsrental_mvc.model.entities.shop.Order;
import com.itexclusive.toolsrental_mvc.model.entities.shop.StockPosition;

public interface OrderService extends DAO<Order>{
    void pay(int id);
    void addPosition(Order order, StockPosition stockPosition, int amount);
    boolean checkQty(int id);
    ShortOrdersDTO getPaidByProfileId(Integer profileId);
}
