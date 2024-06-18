package com.itexclusive.toolsrental_mvc.model.dao.repositories;

import com.itexclusive.toolsrental_mvc.model.entities.shop.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> getItemsByCategoryId(int id);

}
