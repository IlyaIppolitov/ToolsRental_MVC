package com.itexclusive.toolsrental_mvc.model.entities.shop;

import com.itexclusive.toolsrental_mvc.model.security.User;
import jakarta.persistence.*;
import lombok.*;

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
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "item_id")
    @OneToOne()
    @JoinColumn(name = "item_id")
    private Item item;
    private StockPosition(Builder builder) {
        this.amount = builder.amount;
        this.item = builder.item;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer amount;
        private Item item;

        public Builder amount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public Builder item(Item item) {
            this.item = item;
            return this;
        }

        public StockPosition build() {
            return new StockPosition(this);
        }
    }
}
