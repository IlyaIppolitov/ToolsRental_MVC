package com.itexclusive.toolsrental_mvc.model.security;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_EMPLOYEE,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
