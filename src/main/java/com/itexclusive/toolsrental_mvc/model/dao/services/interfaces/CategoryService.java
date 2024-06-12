package com.itexclusive.toolsrental_mvc.model.dao.services.interfaces;

import com.itexclusive.toolsrental_mvc.model.entities.shop.Category;

import java.util.Optional;

public interface CategoryService extends DAO<Category>{
    Category update(Category category);
    Optional<Category> findByName(String name);
}
