package com.itexclusive.toolsrental_mvc.controllers;


import com.itexclusive.toolsrental.dao.category.CategoryService;
import com.itexclusive.toolsrental.model.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    private String categoriesPage(Model model) {
        model.addAttribute("categories", categoryService.all());
        return "/entities/categories";
    }

    @GetMapping("/{id}")
    private String getById(@PathVariable int id, Model model) {
        model.addAttribute("category", categoryService.findById(id).get());
        model.addAttribute("categories", categoryService.all());
        return "/entities/categories";
    }

    @PostMapping
    public String save(Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    @PostMapping("/{id}")
    public String update(Category category) {
        categoryService.update(category);
        return "redirect:/categories";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}
