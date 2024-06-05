package com.itexclusive.toolsrental_mvc.model.entities.user;

import com.itexclusive.toolsrental_mvc.model.entities.shop.Order;
import com.itexclusive.toolsrental_mvc.model.entities.user.dto.ProfileDTO;
import com.itexclusive.toolsrental_mvc.model.security.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "profile_t")
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "username")
    private String username;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Set<Order> orders;

    public void update(ProfileDTO dto){
        this.name = dto.getName();
        this.phone = dto.getPhone();
        this.username = dto.getUsername();
    }
}
