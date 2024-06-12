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

    @GetMapping("/")
    public String home(Model model) {
//        List<CategoryDTO> categories = categoryService.all().stream()
//                .map(c -> CategoryDTO.builder()
//                        .id(c.getId())
//                        .name(c.getName())
//                        .build())
//                .collect(Collectors.toList());
//        model.addAttribute("categories", categories);
        return "ui/pages/index";
    }

    @GetMapping("/login")
    public String login() {
//        public String login(@RequestParam(required = false) String error) {

        return "/ui/pages/login";
    }
}
