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
        User user = userService.findByEmail(authentication.getName()).get();

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
    public String Update(@ModelAttribute(name = "profileDTO") ProfileDTO profileDTO){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Проверка id в запросе с id пользователя
        if (userService.findByEmail(authentication.getName()).get().getId() == profileDTO.getUserId()){

            profileService.update(profileDTO);
        }
        return "redirect:/profile";
    }

    @PostMapping("/update_pass")
    public String UpdatePass(@RequestParam Integer userId,
                             @RequestParam String oldPassword,
                             @RequestParam String password,
                             @RequestParam String passRepeat,
                             RedirectAttributes ra){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Проверка id в запросе с id пользователя
        if (userService.findByEmail(authentication.getName()).get().getId() == userId) {

            if (userService.passwordVerified(userId, oldPassword)) {
                if (password.equals(passRepeat)) {
                    userService.updatePassword(userId, password);
                } else {
                    ra.addFlashAttribute("error", true);
                    ra.addFlashAttribute("type_error", "pass_repeat");
                }
            } else {
                ra.addFlashAttribute("error", true);
                ra.addFlashAttribute("type_error", "wrong_pass");
            }
        } else {
            ra.addFlashAttribute("error", true);
            ra.addFlashAttribute("type_error", "id_error");
        }
        return "redirect:/profile";
    }

    @PostMapping("/delete")
    public String deleteAttempt(@RequestParam int profileId, RedirectAttributes ra){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Проверка id в запросе с id пользователя
        if (userService.findByEmail(authentication.getName()).get().getProfile().getId() == profileId){
            ra.addFlashAttribute("warning", "Вы точно хотите удалить свой аккаунт?");
        } else {
            ra.addFlashAttribute("warning", "Не нужно удалять чужой аккаунт!");
        }
        return "redirect:/profile";
    }
}
