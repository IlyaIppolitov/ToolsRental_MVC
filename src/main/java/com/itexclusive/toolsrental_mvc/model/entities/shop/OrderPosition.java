package com.itexclusive.toolsrental_mvc.model.entities.shop;


import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@Entity
@Table(name = "order_position_t")
@AllArgsConstructor
@NoArgsConstructor
public class OrderPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "amount")
    private Integer amount;
    @ManyToOne
    @JoinColumn(name = "stock_pos_id")
    private StockPosition stockPosition;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderPosition position = (OrderPosition) o;

        Integer stockPositionId = position.stockPosition.getId();
        if (stockPositionId == null ) return false;
        return Objects.equals(this.stockPosition.getId(), stockPositionId);
    }

    @Override
    public int hashCode() {
        int result;
        if (this.stockPosition.getId() != null)
            result = this.stockPosition.getId().hashCode();
        else
            result = 0;
        return result;
    }
}
