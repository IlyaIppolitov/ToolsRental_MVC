package com.itexclusive.toolsrental_mvc.model.entities.shop.DTO;

import com.itexclusive.toolsrental_mvc.model.entities.shop.OrderPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderPositionDTO {
    private int id;
    private String article;
    private String model;
    private double price;
    private String img;
    private String brand;
    private int positionId;
    private int amount;
    private int inStock;

    public OrderPositionDTO (OrderPosition position){
        this.id = position.getId();
        this.article = position.getStockPosition().getItem().getArticle();
        this.model = position.getStockPosition().getItem().getModel();
        this.price = position.getStockPosition().getItem().getPrice();
        this.img = position.getStockPosition().getItem().getImg();
        this.brand = position.getStockPosition().getItem().getBrand().getName();
        this.amount = position.getAmount();
        this.inStock = position.getStockPosition().getAmount();
    }
}
