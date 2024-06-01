package com.itexclusive.toolsrental_mvc.model.dao.services.implementations;

import com.itexclusive.toolsrental_mvc.model.dao.repositories.OrderPositionRepository;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.OrderPositionService;
import com.itexclusive.toolsrental_mvc.model.entities.shop.OrderPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderPositionServiceImplementation implements OrderPositionService {
    private final OrderPositionRepository repo;

    @Override
    public List<OrderPosition> all() {
        return null;
    }

    @Override
    public Optional<OrderPosition> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public OrderPosition save(OrderPosition orderPosition) {
        return repo.save(orderPosition);
    }

    @Override
    public OrderPosition update(OrderPosition orderPosition) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
