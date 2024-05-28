package com.itexclusive.toolsrental_mvc.model.dao.repositories;

import org.klozevitz.phat_mvc.model.entities.shop.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
