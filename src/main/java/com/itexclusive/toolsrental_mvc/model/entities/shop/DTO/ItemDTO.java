package com.itexclusive.toolsrental_mvc.model.entities.shop.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDTO {
    private int id;
    private String article;
    private String model;
    private double price;
    private String brand;
    private int stock;
}
