package com.itexclusive.toolsrental_mvc.model.dao.services.implementations;

import com.itexclusive.toolsrental_mvc.model.dao.repositories.UserRepository;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.UserService;
import com.itexclusive.toolsrental_mvc.model.entities.user.Role;
import com.itexclusive.toolsrental_mvc.model.entities.user.User;
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
        return repo.findAll();
    }

    @Override
    public Optional<User> findById(int id) {
        try {
            return repo.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public User update(User user) {
        try {
            return repo.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            repo.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
