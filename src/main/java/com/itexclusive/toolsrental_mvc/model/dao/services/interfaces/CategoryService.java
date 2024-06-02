package com.itexclusive.toolsrental_mvc.model.dao.services.interfaces;

import com.itexclusive.toolsrental_mvc.model.entities.shop.Category;

public interface CategoryService extends DAO<Category>{
    public Category update(Category category);
}
