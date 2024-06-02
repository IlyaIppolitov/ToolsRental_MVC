package com.itexclusive.toolsrental_mvc.model.dao.services.interfaces;

import com.itexclusive.toolsrental_mvc.model.entities.user.Profile;
import com.itexclusive.toolsrental_mvc.model.entities.user.dto.ProfileDTO;

public interface ProfileService extends DAO<Profile> {

    Profile update(ProfileDTO dto);
}
