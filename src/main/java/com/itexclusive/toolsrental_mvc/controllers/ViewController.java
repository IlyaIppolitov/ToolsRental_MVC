package com.itexclusive.toolsrental_mvc.controllers;

import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.CategoryService;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ViewController {

    private final ItemService itemService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", itemService.getRandom());
        return "/ui/pages/searchResults";
    }

    @GetMapping("/login")
    public String login() {
//        public String login(@RequestParam(required = false) String error) {

        return "/ui/pages/login";
    }
}
