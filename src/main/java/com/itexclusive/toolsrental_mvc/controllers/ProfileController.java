package com.itexclusive.toolsrental_mvc.controllers;

import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.ProfileService;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.UserService;
import com.itexclusive.toolsrental_mvc.model.entities.user.dto.ProfileDTO;
import com.itexclusive.toolsrental_mvc.model.security.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final ProfileService profileService;

    @GetMapping
    public String profilePage(Model model, Authentication authentication){
        User user = userService.findByUsername(authentication.getName()).get();

        ProfileDTO profileDTO = new ProfileDTO(user);
        model.addAttribute("profileDto", profileDTO);
        return "ui/pages/profile";
    }

    // Второй способ получить id пользователя
//    @GetMapping
//    public String profilePage(Model model){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return "redirect:/";
//    }

    @PostMapping("/update")
    public String Update(@ModelAttribute ProfileDTO dto){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Проверка id в запросе с id пользователя
        if (userService.findByUsername(authentication.getName()).get().getId() == dto.getUserId()){
            profileService.update(dto);
        }
        return "redirect:/profile";
    }

    @PostMapping("/delete")
    public String deleteAttempt(@RequestParam int profileId, RedirectAttributes ra){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Проверка id в запросе с id пользователя
        if (userService.findByUsername(authentication.getName()).get().getProfile().getId() == profileId){
            ra.addFlashAttribute("warning", "Вы точно хотите удалить свой аккаунт?");
        } else {
            ra.addFlashAttribute("warning", "Не нужно удалять чужой аккаунт!");
        }
        return "redirect:/profile";
    }
}
