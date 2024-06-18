package com.itexclusive.toolsrental_mvc.controllers;

import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.OrderService;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.UserService;
import com.itexclusive.toolsrental_mvc.model.entities.shop.DTO.OrderDTO;
import com.itexclusive.toolsrental_mvc.model.security.User;
import com.itexclusive.toolsrental_mvc.model.security.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final UserService userService;
    private final OrderService orderService;

    @GetMapping
    public String cartPage(Model model, Authentication authentication) {

        User user = userService.findByEmail(authentication.getName()).get();
        OrderDTO dto = userService.getCurrentOrderDTO(user);

        model.addAttribute("orderDto", dto);
        return "/ui/pages/cart";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        orderService.deleteById(id);
        return "redirect:/categories";
    }
}
