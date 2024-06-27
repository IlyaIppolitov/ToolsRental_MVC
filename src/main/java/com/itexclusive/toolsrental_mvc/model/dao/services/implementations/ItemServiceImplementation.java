package com.itexclusive.toolsrental_mvc.model.dao.services.implementations;

import com.itexclusive.toolsrental_mvc.model.dao.repositories.ItemRepository;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.ItemService;
import com.itexclusive.toolsrental_mvc.model.entities.shop.DTO.ItemDTO;
import com.itexclusive.toolsrental_mvc.model.entities.shop.Item;
import com.itexclusive.toolsrental_mvc.model.entities.shop.StockPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<ItemDTO> getAllByCategoryId(int id) {
        List<Item> items = repo.getItemsByCategoryId(id);

        return mapItemListToItemDtoList(items);
    }

    @Override
    public Integer getStock(int id) {

        var item = repo.findById(id);

        if (item.isEmpty())
            return 0;

        return item.get().getPosition().getAmount();
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

    @Override
    public List<ItemDTO> getAllByQuery(String query){
        List<Item> items = repo.searchByModelOrBrandName(query);

        return mapItemListToItemDtoList(items);
    }

    @Override
    public List<ItemDTO> getRandom(){
        List<Item> items = repo.findRandomItems();
        return mapItemListToItemDtoList(items);
    }

    private List<ItemDTO> mapItemListToItemDtoList(List<Item> items){
        return items.stream()
            .map(item -> ItemDTO.builder()
                .id(item.getId())
                .article(item.getArticle())
                .model(item.getModel())
                .price(item.getPrice())
                .img(item.getImg())
                .brand(item.getBrand().getName())
                .stock(item.getPosition().getAmount())
                .build())
            .toList();
    }
}
