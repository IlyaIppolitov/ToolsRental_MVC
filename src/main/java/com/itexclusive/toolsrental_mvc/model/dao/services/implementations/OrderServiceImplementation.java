package com.itexclusive.toolsrental_mvc.model.dao.services.implementations;

import com.itexclusive.toolsrental_mvc.model.dao.repositories.OrderRepository;
import com.itexclusive.toolsrental_mvc.model.dao.repositories.StockPositionRepository;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.OrderService;
import com.itexclusive.toolsrental_mvc.model.entities.shop.Order;
import com.itexclusive.toolsrental_mvc.model.entities.shop.OrderPosition;
import com.itexclusive.toolsrental_mvc.model.entities.shop.StockPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService {
    private final OrderRepository orderRepository;
    private final StockPositionRepository stockPositionRepository;

    @Override
    public List<Order> all() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void pay(int id) {
        if (orderRepository.existsById(id)) {
            Order orderPaid = orderRepository.findById(id).get();
            orderPaid.setIsPaid(true);
            orderRepository.save(orderPaid);

            Order newBucket = Order.builder()
                    .isPaid(false)
                    .positions(new HashSet<>())
                    .profile(orderPaid.getProfile())
                    .build();
            orderRepository.save(newBucket);
        }
    }

    @Override
    public void addPosition(Order order, StockPosition stockPosition, int amount) {
        OrderPosition orderPosition = OrderPosition.builder()
                .order(order)
                .stockPosition(stockPosition)
                .amount(amount)
                .build();
        order.getPositions().add(orderPosition);
        orderRepository.save(order);

        stockPosition.setAmount(stockPosition.getAmount() - amount);
        stockPositionRepository.save(stockPosition);
    }


}
