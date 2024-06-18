package com.itexclusive.toolsrental_mvc.controllers;

import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.ItemService;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.OrderService;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.UserService;
import com.itexclusive.toolsrental_mvc.model.entities.shop.DTO.ItemIdDTO;
import com.itexclusive.toolsrental_mvc.model.entities.shop.StockPosition;
import com.itexclusive.toolsrental_mvc.model.security.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class RestCartController {

    private final UserService userService;
    private final ItemService itemService;
    private final OrderService orderService;
    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody ItemIdDTO itemIdDTO, Authentication authentication) {
        try {
            User user = userService.findByEmail(authentication.getName()).get();
            var order = userService.getCurrentOrder(user);
            StockPosition position = itemService.findById(itemIdDTO.getId()).get().getPosition();
            orderService.addPosition(order, position,1);
            return ResponseEntity.ok("Товар добавлен в корзину");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка добавления товара");
        }
    }
}
