package com.itexclusive.toolsrental_mvc.model.entities.shop.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryDTO {
    private int id;
    private String name;
    private String description;
}
