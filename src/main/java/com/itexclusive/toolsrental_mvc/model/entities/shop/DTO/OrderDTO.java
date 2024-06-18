package com.itexclusive.toolsrental_mvc.model.entities.shop.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    List<ItemDTO> items;
    Double total;
    boolean isPaid;
}
