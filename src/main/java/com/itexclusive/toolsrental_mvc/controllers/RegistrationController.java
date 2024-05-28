package com.itexclusive.toolsrental_mvc.controllers;

import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.UserService;
import com.itexclusive.toolsrental_mvc.model.entities.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping
    public String registrationPage() {
        return "/ui/pages/registration";
    }

    // сделать с редиректом и RedirectAttributes
    @PostMapping
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String passRepeat, Model model) {
        if (!password.equals(passRepeat)) {
            model.addAttribute("error", true);
            model.addAttribute("type", "password");
            return "registration";
        }

        User registered = userService.save(new User(username, password));

        if (registered == null) {
            model.addAttribute("error", true);
            model.addAttribute("type", "username");
            return "registration";
        }

        return "redirect:/";
    }
}
