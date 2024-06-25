package com.itexclusive.toolsrental_mvc.model.dao.repositories;

import com.itexclusive.toolsrental_mvc.model.entities.shop.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> getItemsByCategoryId(int id);

    @Query("SELECT i FROM Item i JOIN i.brand b WHERE LOWER(i.model) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(b.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Item> searchByModelOrBrandName(@Param("query") String query);


    // Custom query to fetch 4 random items
    @Query("SELECT i FROM Item i ORDER BY RANDOM() LIMIT 4")
    List<Item> findRandomItems();
}
