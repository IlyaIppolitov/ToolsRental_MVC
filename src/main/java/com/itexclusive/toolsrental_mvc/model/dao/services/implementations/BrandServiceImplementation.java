package com.itexclusive.toolsrental_mvc.model.dao.services.implementations;

import com.itexclusive.toolsrental_mvc.model.dao.repositories.BrandRepository;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.BrandService;
import com.itexclusive.toolsrental_mvc.model.entities.shop.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandServiceImplementation implements BrandService {
    private final BrandRepository repo;

    @Override
    public List<Brand> all() {
        return repo.findAll();
    }

    @Override
    public Optional<Brand> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Brand save(Brand brand) {
        try {
            return repo.save(brand);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Brand update(Brand brand) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
