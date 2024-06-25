package com.itexclusive.toolsrental_mvc.model.entities.shop.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortOrderDTO {
    Integer orderId;
    Double price;
}
