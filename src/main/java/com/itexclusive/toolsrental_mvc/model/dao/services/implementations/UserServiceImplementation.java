package com.itexclusive.toolsrental_mvc.model.dao.services.implementations;

import com.itexclusive.toolsrental_mvc.model.dao.repositories.UserRepository;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.UserService;
import com.itexclusive.toolsrental_mvc.model.security.Role;
import com.itexclusive.toolsrental_mvc.model.security.User;
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
        if (repo.findByEmail(user.getUsername()) == null) {
            user.setPassword(encoder.encode(user.getPassword()));
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
        return repo.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String email) {
        var foundUser = repo.findByEmail(email);
        if (foundUser == null)
            return Optional.empty();
        return Optional.of(foundUser);
    }

    @Override
    public void updateUsername(int id, String email) {

        User userToUpdate = repo.findById(id).get();
        userToUpdate.setEmail(email);
        repo.save(userToUpdate);
    }

    @Override
    public void updatePassword(int id, String password) {

        User userToUpdate = repo.findById(id).get();
        userToUpdate.setPassword(encoder.encode(password));
        repo.save(userToUpdate);
    }

//    @Override
//    public User update(User user) {
//        try {
//            return repo.save(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }



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
