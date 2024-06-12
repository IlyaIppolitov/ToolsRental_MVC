package com.itexclusive.toolsrental_mvc.model.security;

import com.itexclusive.toolsrental_mvc.model.entities.shop.Order;
import com.itexclusive.toolsrental_mvc.model.entities.user.Profile;
import com.itexclusive.toolsrental_mvc.model.security.dto.UserDataDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Getter
@Setter
@Builder
@Entity
@Table(name = "user_t")
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Profile profile;

    public User(String username, String email, String password) {
        this.email = email;
        this.password = password;
        this.role = Role.ROLE_USER;
        this.profile = Profile.builder()
            .user(this)
            .username(username)
            .orders(new HashSet<>() {{
                add(Order.builder()
                    .isPaid(false)
                    .build());
            }})
            .build();
    }

    public User(String username, String email, Role role, String password) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.profile = Profile.builder()
            .user(this)
            .username(username)
            .orders(new HashSet<>() {{
                add(Order.builder()
                    .isPaid(false)
                    .build());
            }})
            .build();
    }

    public UserDetails securityUserFromEntity(String email){
        return new org.springframework.security.core.userdetails.User(
            email,
            this.password,
            true,
            true,
            true,
            true,
            new ArrayList<>(){{add(role);}}
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>() {{add(new SimpleGrantedAuthority(role.name()));}};
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
