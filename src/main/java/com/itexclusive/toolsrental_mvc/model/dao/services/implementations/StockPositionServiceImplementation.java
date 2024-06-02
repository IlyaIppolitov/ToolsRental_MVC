package com.itexclusive.toolsrental_mvc.model.dao.services.implementations;

import com.itexclusive.toolsrental_mvc.model.dao.repositories.StockPositionRepository;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.StockPositionService;
import com.itexclusive.toolsrental_mvc.model.entities.shop.StockPosition;
import lombok.RequiredArgsConstructor;
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

    public StockPosition update(StockPosition stockPosition) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
