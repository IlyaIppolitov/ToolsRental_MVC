package com.itexclusive.toolsrental_mvc.model.entities.shop.DTO;

import lombok.Builder;
import lombok.Data;
import org.klozevitz.phat_mvc.model.entities.shop.itemAttributes.Sex;

@Data
@Builder
public class ItemDTO {
    private int id;
    private String article;
    private String model;
    private double price;
    private Sex sex;
    private String brand;
}
