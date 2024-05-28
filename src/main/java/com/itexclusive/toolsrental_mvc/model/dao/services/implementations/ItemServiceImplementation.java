package com.itexclusive.toolsrental_mvc.model.dao.services.implementations;

import lombok.RequiredArgsConstructor;
import org.klozevitz.phat_mvc.model.dao.repositories.ItemRepository;
import org.klozevitz.phat_mvc.model.dao.services.interfaces.ItemService;
import org.klozevitz.phat_mvc.model.entities.shop.Item;
import org.klozevitz.phat_mvc.model.entities.shop.StockPosition;
import org.klozevitz.phat_mvc.model.entities.shop.itemAttributes.Color;
import org.klozevitz.phat_mvc.model.entities.shop.itemAttributes.Size;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

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
    public int[][] getStockMap(int id) {
        Item item = repo.findById(id).get();
        Set<StockPosition> positions = item.getPositions();
        Size byCategory = getCategory(item.getCategory().getName());
        int[][] result = new int[byCategory.getSizes().size()][Color.values().length];
        positions.forEach(p ->
                result[tempCol(byCategory.getSizes(), p.getSize())][tempStr(p.getColor())] = p.getAmount());
        return result;
    }

    private Size getCategory(String category) {
        return switch (category) {
            case "ГОЛОВНЫЕ УБОРЫ" -> Size.HATS;
            case "ОДЕЖДА" -> Size.CLOTHES;
            case "ОБУВЬ" -> Size.SHOES;
            default -> null;
        };
    }

    private int tempStr(Color color) {
        Color[] values = Color.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i] == color) {
                return i;
            }
        }
        return -1;
    }

    private int tempCol(List<String> sizes, String size) {
        AtomicInteger index = new AtomicInteger(0);
        AtomicReference<Boolean> found = new AtomicReference<>(false);
        sizes.forEach(s -> {
            if (s.equals(size)) {
                found.set(true);
            }
            if (!found.get()) {
                index.getAndIncrement();
            }
        });
        return index.get();
    }

    @Override
    public Item update(Item item) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
