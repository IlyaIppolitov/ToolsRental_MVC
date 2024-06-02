package com.itexclusive.toolsrental_mvc.model.dao.services.implementations;

import com.itexclusive.toolsrental_mvc.model.dao.repositories.ProfileRepository;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.ProfileService;
import com.itexclusive.toolsrental_mvc.model.entities.user.Profile;
import com.itexclusive.toolsrental_mvc.model.entities.user.dto.ProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImplementation implements ProfileService {

    private final ProfileRepository repo;

    @Override
    public List<Profile> all() {
        return repo.findAll();
    }

    @Override
    public Optional<Profile> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Profile save(Profile profile) {
        return null;
    }

    @Override
    public Profile update(ProfileDTO dto) {

        // Здесь проверка не является обязательной, так как выше проверка уже пройдена
        Profile toUpdate = repo.findById(dto.getId()).get();
        toUpdate.update(dto);
        return repo.save(toUpdate);
    }

    @Override
    public boolean deleteById(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
