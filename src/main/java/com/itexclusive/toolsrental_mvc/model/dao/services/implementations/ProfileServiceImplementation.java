package com.itexclusive.toolsrental_mvc.model.dao.services.implementations;

import com.itexclusive.toolsrental_mvc.model.dao.repositories.ProfileRepository;
import com.itexclusive.toolsrental_mvc.model.dao.repositories.UserRepository;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.ProfileService;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.UserService;
import com.itexclusive.toolsrental_mvc.model.entities.user.Profile;
import com.itexclusive.toolsrental_mvc.model.entities.user.dto.ProfileDTO;
import com.itexclusive.toolsrental_mvc.model.security.dto.UserDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImplementation implements ProfileService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Override
    public List<Profile> all() {
        return profileRepository.findAll();
    }

    @Override
    public Optional<Profile> findById(int id) {
        return profileRepository.findById(id);
    }

    @Override
    public Profile save(Profile profile) {
        return null;
    }

    @Override
    public Profile update(ProfileDTO dto) {

        // Здесь проверка не является обязательной, так как выше проверка уже пройдена
        Profile toUpdate = profileRepository.findById(dto.getId()).get();
        toUpdate.update(dto);
        return profileRepository.save(toUpdate);
    }

    @Override
    public Profile update(UserDataDTO dto) {

        try{
            var profileToUpdate = userRepository.findById(dto.getId()).get().getProfile();
            profileToUpdate.setUsername(dto.getUsername());
            return profileRepository.save(profileToUpdate);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        if (profileRepository.existsById(id)) {
            profileRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
