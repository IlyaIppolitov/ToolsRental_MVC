package com.itexclusive.toolsrental_mvc.controllers;


import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.UserService;
import com.itexclusive.toolsrental_mvc.model.security.dto.UserDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping
    public String adminPage(Model model){

        var users = userService.all();

        var dto = users.stream()
            .map(UserDataDTO::new)
            .toList();

        model.addAttribute("users", dto);
        return "ui/pages/admin";
    }


}
