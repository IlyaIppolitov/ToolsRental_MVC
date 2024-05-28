package com.itexclusive.toolsrental_mvc.model.dao.services.interfaces;

import org.klozevitz.phat_mvc.model.entities.shop.Item;

import java.util.List;

public interface ItemService extends DAO<Item> {
    List<Item> getAllByCategoryId(int id);
    int[][] getStockMap(int id);
}
