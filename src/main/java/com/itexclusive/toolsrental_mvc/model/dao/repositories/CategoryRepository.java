package com.itexclusive.toolsrental_mvc.model.dao.repositories;

import org.klozevitz.phat_mvc.model.entities.shop.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
