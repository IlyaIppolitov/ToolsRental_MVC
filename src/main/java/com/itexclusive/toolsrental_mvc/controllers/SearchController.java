package com.itexclusive.toolsrental_mvc.controllers;


import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final ItemService itemService;

    @GetMapping
    private String searchResultsPage(@RequestParam(name = "query", required = false) String query, Model model) {
        model.addAttribute("items", itemService.getAllByQuery(query));
        return "/ui/pages/searchResults";
    }
}
