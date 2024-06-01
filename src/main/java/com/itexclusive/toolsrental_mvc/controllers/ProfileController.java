package com.itexclusive.toolsrental_mvc.controllers;

import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.UserService;
import com.itexclusive.toolsrental_mvc.model.entities.user.ProfileDTO;
import com.itexclusive.toolsrental_mvc.model.security.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping
    public String profilePage(Model model, Authentication authentication){
        User user = userService.findByUsername(authentication.getName()).get();

        ProfileDTO profileDTO = new ProfileDTO(user);
        model.addAttribute("profile", profileDTO);
        return "redirect:/";
    }

    // Второй способ получить id пользователя
//    @GetMapping
//    public String profilePage(Model model){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return "redirect:/";
//    }
}
