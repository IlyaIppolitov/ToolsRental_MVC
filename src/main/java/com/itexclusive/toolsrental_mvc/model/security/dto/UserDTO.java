package com.itexclusive.toolsrental_mvc.model.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private String email;
    private String password;
    private String matchPass;
}
