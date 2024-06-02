package com.itexclusive.toolsrental_mvc.model.security;

import com.itexclusive.toolsrental_mvc.model.dao.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImplementation implements UserDetailsService {
    private final UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Методом get можем пользоваться так какне сможем сюда попасть без пользователя
        User loadedUser = repo.findByUsername(username).get();
        org.springframework.security.core.userdetails.UserDetails securityUser =
            loadedUser.securityUserFromEntity(username);
        return securityUser;
    }
}
