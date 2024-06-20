package com.itexclusive.toolsrental_mvc.controllers;

import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.*;
import com.itexclusive.toolsrental_mvc.model.entities.shop.DTO.ItemIdDTO;
import com.itexclusive.toolsrental_mvc.model.entities.shop.Order;
import com.itexclusive.toolsrental_mvc.model.entities.shop.OrderPosition;
import com.itexclusive.toolsrental_mvc.model.entities.shop.StockPosition;
import com.itexclusive.toolsrental_mvc.model.security.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;
    private final UserService userService;
    private final ItemService itemService;
    private final StockPositionService stockPositionService;
    private final OrderPositionService orderPositionService;

    @PostMapping("/update")
    public void update(@RequestBody ItemIdDTO itemIdDTO) {
        var position = orderPositionService.findById(itemIdDTO.getId());
        if (position.isPresent()){
            position.get().setAmount(itemIdDTO.getAmount());
            orderPositionService.save(position.get());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrderPosition(@RequestBody ItemIdDTO itemIdDTO, Authentication authentication) {
        try {
            User user = userService.findByEmail(authentication.getName()).get();
            var order = userService.getCurrentOrder(user);
            StockPosition position = itemService.findById(itemIdDTO.getId()).get().getPosition();
            orderService.addPosition(order, position, itemIdDTO.getAmount());
            return ResponseEntity.ok("Товар добавлен в корзину");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка добавления товара");
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteOrderPosition(@RequestBody ItemIdDTO itemIdDTO, Authentication authentication) {
        try {
            orderPositionService.deleteById(itemIdDTO.getId());
            return ResponseEntity.ok("Товар удалён");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка удаления товара");
        }
    }

}
