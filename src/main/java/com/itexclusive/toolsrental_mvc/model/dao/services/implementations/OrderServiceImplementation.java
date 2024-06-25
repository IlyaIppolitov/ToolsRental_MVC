package com.itexclusive.toolsrental_mvc.model.dao.services.implementations;

import com.itexclusive.toolsrental_mvc.model.dao.repositories.OrderRepository;
import com.itexclusive.toolsrental_mvc.model.dao.repositories.StockPositionRepository;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.OrderService;
import com.itexclusive.toolsrental_mvc.model.entities.shop.DTO.ItemDTO;
import com.itexclusive.toolsrental_mvc.model.entities.shop.DTO.ShortOrderDTO;
import com.itexclusive.toolsrental_mvc.model.entities.shop.DTO.ShortOrdersDTO;
import com.itexclusive.toolsrental_mvc.model.entities.shop.Item;
import com.itexclusive.toolsrental_mvc.model.entities.shop.Order;
import com.itexclusive.toolsrental_mvc.model.entities.shop.OrderPosition;
import com.itexclusive.toolsrental_mvc.model.entities.shop.StockPosition;
import com.itexclusive.toolsrental_mvc.model.security.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
            if (orderPaid.getPositions().isEmpty())
                return;

            double price = 0.;
            for (OrderPosition position : orderPaid.getPositions()){

                StockPosition stockPosition = position.getStockPosition();
                price += stockPosition.getItem().getPrice() * position.getAmount();
                stockPosition.setAmount(stockPosition.getAmount() - position.getAmount());
                stockPositionRepository.save(stockPosition);
            }
            orderPaid.setIsPaid(true);
            orderPaid.setPrice(price);
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
        Set<OrderPosition> positions = order.getPositions();
        if (positions.contains(orderPosition)){
            return;
        }
        order.getPositions().add(orderPosition);
        orderRepository.save(order);
    }

    @Override
    public boolean checkQty(int id) {
        if (orderRepository.existsById(id)) {

            Order order = orderRepository.findById(id).get();

            if (order.getPositions().isEmpty())
                return true;

            for (OrderPosition position : order.getPositions()){

                Integer stockAmount = position.getStockPosition().getAmount();
                Integer orderAmount = position.getAmount();
                if (stockAmount < orderAmount)
                    return false;
            }
            return true;
        }
        return false;
    }

    public ShortOrdersDTO getPaidByProfileId(Integer profileId){
        var orders = orderRepository.getOrdersByProfile_IdEqualsAndIsPaidEquals(profileId, true);
        var ordersData = orders.stream()
            .map(order -> ShortOrderDTO.builder()
                .orderId(order.getId())
                .price(order.getPrice())
                .build())
            .toList();
        return new ShortOrdersDTO(ordersData);
    }

    private List<ItemDTO> mapItemListToItemDtoList(List<Item> items){
        return items.stream()
            .map(item -> ItemDTO.builder()
                .id(item.getId())
                .article(item.getArticle())
                .model(item.getModel())
                .price(item.getPrice())
                .brand(item.getBrand().getName())
                .stock(item.getPosition().getAmount())
                .build())
            .toList();
    }


}
