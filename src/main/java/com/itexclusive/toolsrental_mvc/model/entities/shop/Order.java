package com.itexclusive.toolsrental_mvc.model.entities.shop;

import com.itexclusive.toolsrental_mvc.model.entities.user.Profile;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "order_t")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "isPaid")
    private Boolean isPaid;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Set<OrderPosition> positions;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Profile profile;
}
