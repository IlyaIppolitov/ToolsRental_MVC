package com.itexclusive.toolsrental_mvc.model.entities.shop;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "item_t")
@AllArgsConstructor
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
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<StockPosition> positions = new HashSet<>();

    public Item() {
        positions = new HashSet<>();
    }
}
