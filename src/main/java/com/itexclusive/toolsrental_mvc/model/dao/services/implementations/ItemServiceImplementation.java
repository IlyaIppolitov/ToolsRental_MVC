package com.itexclusive.toolsrental_mvc.model.dao.services.implementations;

import com.itexclusive.toolsrental_mvc.model.dao.repositories.ItemRepository;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.ItemService;
import com.itexclusive.toolsrental_mvc.model.entities.shop.Item;
import com.itexclusive.toolsrental_mvc.model.entities.shop.StockPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImplementation implements ItemService {
    private final ItemRepository repo;

    @Override
    public List<Item> all() {
        return repo.findAll();
    }

    @Override
    public Optional<Item> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Item save(Item item) {
        try {
            return repo.save(item);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Item> getAllByCategoryId(int id) {
        return repo.getItemsByCategoryId(id);
    }

    @Override
    public List<StockPosition> getStock(int id) {
        var item = repo.findById(id);
        return item.map(value -> new ArrayList<>(value.getPositions()))
                    .orElse(null);
    }

    public Item update(Item item) {
        try {
            return repo.save(item);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            repo.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
