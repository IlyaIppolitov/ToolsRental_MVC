package com.itexclusive.toolsrental_mvc.model.entities.user.dto;

import com.itexclusive.toolsrental_mvc.model.entities.user.Profile;
import com.itexclusive.toolsrental_mvc.model.security.User;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProfileDTO {
    private Integer id;
    private String phone;
    private String username;
    private Integer userId;
    private Set<Integer> orderIds;
    private String email;

    public ProfileDTO(User user){
        Profile profile = user.getProfile();

        this.id = profile.getId();
        this.phone = profile.getPhone();
        this.username = profile.getUsername();
        this.userId = user.getId();
        this.email = user.getEmail();
        this.orderIds = profile
            .getOrders()
            .stream()
            .map(order -> order.getId())
            .collect(Collectors.toSet());
    }

    public void Update(String fio, String phone, String username, String email){

    }
}
