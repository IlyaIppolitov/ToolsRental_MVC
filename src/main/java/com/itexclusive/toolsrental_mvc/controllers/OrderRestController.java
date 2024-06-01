package com.itexclusive.toolsrental_mvc.controllers;

import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.OrderPositionService;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.OrderService;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.StockPositionService;
import com.itexclusive.toolsrental_mvc.model.entities.shop.Order;
import com.itexclusive.toolsrental_mvc.model.entities.shop.OrderPosition;
import com.itexclusive.toolsrental_mvc.model.entities.shop.StockPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;
    private final StockPositionService stockPositionService;
    private final OrderPositionService orderPositionService;

    @PostMapping("/addPosition/{id}")
    public void addPosition(int stockPosId, int amount, @PathVariable int id) {
        Order order = orderService.findById(id).get();
        StockPosition position = stockPositionService.findById(stockPosId).get();
        if (position.getAmount() >= amount) {
            orderService.addPosition(order, position, amount);
        }
    }

    @PostMapping("/deletePosition/{id}")
    public void deletePosition(int orderPosId, int amount, @PathVariable int id) {
        OrderPosition orderPosition = orderPositionService.findById(orderPosId).get();

        if (amount <= orderPosition.getAmount()) {
            StockPosition stockPosition = orderPosition.getStockPosition();
            stockPosition.setAmount(stockPosition.getAmount() + amount);
            stockPositionService.save(stockPosition);

            if (amount < orderPosition.getAmount()) {
                orderPosition.setAmount(orderPosition.getAmount() - amount);
                orderPositionService.save(orderPosition);
            } else {
                orderPositionService.deleteById(orderPosId);
            }
        }
    }

}
