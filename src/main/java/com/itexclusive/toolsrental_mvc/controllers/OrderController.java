package com.itexclusive.toolsrental_mvc.controllers;

import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.OrderService;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.UserService;
import com.itexclusive.toolsrental_mvc.model.entities.shop.DTO.OrderDTO;
import com.itexclusive.toolsrental_mvc.model.security.User;
import com.itexclusive.toolsrental_mvc.model.entities.shop.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping
    public String orderPage(Model model, Authentication authentication) {

        User user = userService.findByEmail(authentication.getName()).get();
        OrderDTO dto = userService.getCurrentOrderDTO(user);

        model.addAttribute("orderDto", dto);
        return "/ui/pages/order";
    }

    @PostMapping("/submit")
    public String submitForm(@RequestParam Integer orderId, RedirectAttributes ra) {

        if (orderService.checkQty(orderId)){
            orderService.pay(orderId);
            return "redirect:/profile";
        } else {
            ra.addFlashAttribute("error", true);
            ra.addFlashAttribute("type_error", "qty");
            return "redirect:/order";
        }
    }

    @GetMapping("/{id}")
    private String categoryIdPage(@PathVariable Integer id, Model model) {
        model.addAttribute("orderDto", orderService.getOrderDTOById(id));
        return "/ui/pages/old_order";
    }
}
