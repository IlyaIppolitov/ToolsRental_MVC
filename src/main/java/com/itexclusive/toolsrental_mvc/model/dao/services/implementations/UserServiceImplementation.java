package com.itexclusive.toolsrental_mvc.model.dao.services.implementations;

import com.itexclusive.toolsrental_mvc.model.dao.repositories.OrderRepository;
import com.itexclusive.toolsrental_mvc.model.dao.repositories.UserRepository;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.UserService;
import com.itexclusive.toolsrental_mvc.model.entities.shop.DTO.ItemDTO;
import com.itexclusive.toolsrental_mvc.model.entities.shop.DTO.OrderDTO;
import com.itexclusive.toolsrental_mvc.model.entities.shop.Order;
import com.itexclusive.toolsrental_mvc.model.entities.user.Profile;
import com.itexclusive.toolsrental_mvc.model.security.Role;
import com.itexclusive.toolsrental_mvc.model.security.User;
import com.itexclusive.toolsrental_mvc.model.security.dto.UserDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository repo;
    private final OrderRepository orderRepository;
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
    public Optional<User> findByEmail(String email) {
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

    @Override
    public User update(UserDataDTO userData) {
        try {
            User userToUpdate = repo.findById(userData.getId()).get();
            userToUpdate.setEmail(userData.getEmail());
            userToUpdate.setRole(Role.valueOf(userData.getRole()));
            return repo.save(userToUpdate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean passwordVerified(int id, String password) {

        User userToVerify = repo.findById(id).get();
        return encoder.matches(password, userToVerify.getPassword());
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

    @Override
    public Order getCurrentOrder(User user){
        Profile profile = user.getProfile();
        return orderRepository.getOrderByProfileEqualsAndIsPaidEquals(profile, false);
    }

    @Override
    public OrderDTO getCurrentOrderDTO(User user){
        var currentOrder = getCurrentOrder(user);
        List<ItemDTO> items = currentOrder.getPositions().stream()
            .map(orderPosition ->
                new ItemDTO(orderPosition.getStockPosition().getItem())
            )
            .toList();
        Double total = items
            .stream()
            .mapToDouble(ItemDTO::getPrice)
            .sum();
        return new OrderDTO(items, total, false);
    }
}
