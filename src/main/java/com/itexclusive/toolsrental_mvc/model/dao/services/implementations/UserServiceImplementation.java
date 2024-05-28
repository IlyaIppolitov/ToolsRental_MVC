package com.itexclusive.toolsrental_mvc.model.dao.services.implementations;

import com.itexclusive.toolsrental.model.entities.Role;
import com.itexclusive.toolsrental.model.entities.User;
import com.itexclusive.toolsrental.model.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;


    @Override
    public User save(User user) {
        if (repo.findByUsername(user.getUsername()) == null) {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRole(Role.ROLE_USER);
            return repo.save(user);
        }
        return null;
    }

    @Override
    public List<User> all() {
        return null;
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.empty();
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
