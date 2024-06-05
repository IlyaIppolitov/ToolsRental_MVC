package com.itexclusive.toolsrental_mvc.controllers;

import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.UserService;
import com.itexclusive.toolsrental_mvc.model.security.User;
import com.itexclusive.toolsrental_mvc.model.security.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping
    public String registrationPage(Model model) {

        model.addAttribute("user", new UserDTO());
        return "/ui/pages/registration";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String passRepeat,
                           RedirectAttributes ra) {
        if (!password.equals(passRepeat)) {
            ra.addFlashAttribute("error", true);
            ra.addFlashAttribute("type", "password");
            return "redirect:/register";

        }

        User registered = userService.save(new User(username, email, password));

        if (registered == null) {
            ra.addFlashAttribute("error", true);
            ra.addFlashAttribute("type", "email");
            return "redirect:/register";
        }

        return "redirect:/";
    }
}
