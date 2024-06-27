package com.itexclusive.toolsrental_mvc.model.entities.shop.DTO;

import com.itexclusive.toolsrental_mvc.model.entities.shop.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private int id;
    private String article;
    private String model;
    private double price;
    private String img;
    private String brand;
    private int positionId;
    private int stock;

    public ItemDTO (Item item){
        this.id = item.getId();
        this.article = item.getArticle();
        this.model = item.getModel();
        this.price = item.getPrice();
        this.img = item.getImg();
        this.brand = item.getBrand().getName();
        this.positionId = item.getPosition().getId();
        this.stock = item.getPosition().getAmount();
    }

}
