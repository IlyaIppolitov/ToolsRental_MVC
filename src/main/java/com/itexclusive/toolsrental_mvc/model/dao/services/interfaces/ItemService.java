package com.itexclusive.toolsrental_mvc.model.dao.services.interfaces;

import com.itexclusive.toolsrental_mvc.model.entities.shop.Item;
import com.itexclusive.toolsrental_mvc.model.entities.shop.StockPosition;

import java.util.List;
import java.util.Set;

public interface ItemService extends DAO<Item> {
    List<Item> getAllByCategoryId(int id);
    List<StockPosition> getStock(int id);
}
