package com.itexclusive.toolsrental_mvc.controllers;

import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.CategoryService;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ViewController {
    private final CategoryService categoryService;
    private final ItemService itemService;

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

    @GetMapping("/category/{id}")
    public String categoryCatalogue(@PathVariable int id, Model model) {
//        List<ItemDTO> items = categoryService.findById(id).get()
//                .getStock().stream()
//                .map(item -> ItemDTO.builder()
//                        .id(item.getId())
//                        .article(item.getArticle())
//                        .model(item.getModel())
//                        .price(item.getPrice())
//                        .sex(item.getSex())
//                        .brand(item.getBrand().getName())
//                        .build())
//                .toList();
//        model.addAttribute("items", items);
        return "/ui/pages/searchResults";
    }

    @GetMapping("/item/{id}")
    public String itemCard(@PathVariable int id, Model model) {
//        int[][] stockMap = itemService.getStockMap(id);
//        model.addAttribute("stockMap", stockMap);
//        model.addAttribute("item", itemService.findById(id).map(item -> ItemDTO.builder()
//                        .id(item.getId())
//                        .article(item.getArticle())
//                        .model(item.getModel())
//                        .price(item.getPrice())
//                        .sex(item.getSex())
//                        .brand(item.getBrand().getName())
//                        .build()
//                )
//        );
        return "/ui/pages/itemCard";
    }

    @GetMapping("/login")
    public String login() {
//        public String login(@RequestParam(required = false) String error) {

        return "/ui/pages/login";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "/ui/pages/checkout";
    }
}
