package com.itexclusive.toolsrental_mvc.model.entities.shop;

import com.itexclusive.toolsrental_mvc.model.entities.user.Profile;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "item_t")
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "article", unique = true)
    private String article;
    @Column(name = "model")
    private String model;
    @Column(name = "price")
    private Double price;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private StockPosition position;
//    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
//    private Set<StockPosition> positions = new HashSet<>();


    private Item(Builder builder) {
        this.article = builder.article;
        this.model = builder.model;
        this.price = builder.price;
        this.brand = builder.brand;
        this.category = builder.category;
        this.position = builder.position;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String article;
        private String model;
        private Double price;
        private Brand brand;
        private Category category;
        private StockPosition position;

        public Builder article(String article) {
            this.article = article;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Builder brand(Brand brand) {
            this.brand = brand;
            return this;
        }

        public Builder category(Category category) {
            this.category = category;
            return this;
        }

        public Builder position(StockPosition position) {
            this.position = position;
            return this;
        }

        public Item build() {
            Item item = new Item(this);
            if (this.position != null) {
                this.position.setItem(item); // Ensure the bidirectional relationship is set
            }
            return item;
        }
    }

}
