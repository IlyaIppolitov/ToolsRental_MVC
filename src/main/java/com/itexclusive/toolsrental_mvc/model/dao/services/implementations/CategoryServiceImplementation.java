package com.itexclusive.toolsrental_mvc.model.dao.services.implementations;

import com.itexclusive.toolsrental_mvc.model.dao.repositories.CategoryRepository;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.CategoryService;
import com.itexclusive.toolsrental_mvc.model.entities.shop.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryService {
    private final CategoryRepository repo;

    @Override
    public List<Category> all() {
        return repo.findAll();
    }

    @Override
    public Optional<Category> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Category save(Category category) {
        try {
            return repo.save(category);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Category update(Category category) {
        return null;
    }

    @Override
    public Optional<Category> findByName(String name) {
        return repo.findCategoryByName(name);
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
