package com.itexclusive.toolsrental_mvc.model.dao.services.implementations;

import lombok.RequiredArgsConstructor;
import org.klozevitz.phat_mvc.model.dao.repositories.StockPositionRepository;
import org.klozevitz.phat_mvc.model.dao.services.interfaces.StockPositionService;
import org.klozevitz.phat_mvc.model.entities.shop.StockPosition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockPositionServiceImplementation implements StockPositionService {
    private final StockPositionRepository repo;

    @Override
    public List<StockPosition> all() {
        return repo.findAll();
    }

    @Override
    public Optional<StockPosition> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public StockPosition save(StockPosition stockPosition) {
        return repo.save(stockPosition);
    }

    @Override
    public StockPosition update(StockPosition stockPosition) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
