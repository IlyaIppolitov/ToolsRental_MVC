package com.itexclusive.toolsrental_mvc.model.dao.repositories;

import com.itexclusive.toolsrental_mvc.model.entities.shop.StockPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPositionRepository extends JpaRepository<StockPosition, Integer> {
}
