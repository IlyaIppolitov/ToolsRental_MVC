package com.itexclusive.toolsrental_mvc.model.dao.repositories;

import com.itexclusive.toolsrental_mvc.model.entities.shop.OrderPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPositionRepository extends JpaRepository<OrderPosition, Integer> {
    public void deleteOrderPositionByOrder_IdAndStockPosition_Id(Integer orderId, Integer positionId);
}
