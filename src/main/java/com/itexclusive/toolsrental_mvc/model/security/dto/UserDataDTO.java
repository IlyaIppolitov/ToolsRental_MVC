package com.itexclusive.toolsrental_mvc.model.security.dto;

import com.itexclusive.toolsrental_mvc.model.security.Role;
import com.itexclusive.toolsrental_mvc.model.security.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.SpringVersion;

@Getter
@Setter
@NoArgsConstructor
public class UserDataDTO {
    private Integer id;
    private String username;
    private String email;
    private String role;

    public UserDataDTO(User user){
        this.id = user.getId();
        this.username = user.getProfile().getUsername();
        this.email = user.getEmail();
        this.role = user.getRole().toString();
    }
}
