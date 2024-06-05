package com.itexclusive.toolsrental_mvc.model.entities.user.dto;

import com.itexclusive.toolsrental_mvc.model.entities.user.Profile;
import com.itexclusive.toolsrental_mvc.model.security.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class ProfileDTO {
    private Integer id;
    private String name;
    private String phone;
    private String username;
    private int userId;
    private Set<Integer> orderIds;

    public ProfileDTO(User user){
        Profile profile = user.getProfile();

        this.id = profile.getId();
        this.name = profile.getName();
        this.phone = profile.getPhone();
        this.username = profile.getUsername();
        this.userId = user.getId();
        this.orderIds = profile
            .getOrders()
            .stream()
            .map(order -> order.getId())
            .collect(Collectors.toSet());
    }
}
