package com.itexclusive.toolsrental_mvc.model.entities.shop.DTO;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemIdDTOListWrapper {
    private List<ItemIdDTO> items;
}
