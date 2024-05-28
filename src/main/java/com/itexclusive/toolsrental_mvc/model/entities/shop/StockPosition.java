package com.itexclusive.toolsrental_mvc.model.entities.shop;

import jakarta.persistence.*;
import lombok.*;
import org.klozevitz.phat_mvc.model.entities.shop.itemAttributes.Color;

@Getter
@Setter
@Builder
@Entity
@Table(name = "stock_position_t")
@NoArgsConstructor
@AllArgsConstructor
public class StockPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private Color color;
    @Column(name = "size")
    private String size;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "item_id")
    private Item item;
}
