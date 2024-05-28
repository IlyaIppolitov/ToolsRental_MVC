package com.itexclusive.toolsrental_mvc.model.entities.shop;


import jakarta.persistence.*;
import lombok.*;

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
}
