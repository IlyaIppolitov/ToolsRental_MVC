package com.itexclusive.toolsrental_mvc.model.dao.services.interfaces;

import com.itexclusive.toolsrental_mvc.model.entities.shop.Brand;
import com.itexclusive.toolsrental_mvc.model.entities.shop.Category;

import java.util.Optional;

public interface BrandService extends DAO<Brand>{
    Optional<Brand> findByName(String name);
}
