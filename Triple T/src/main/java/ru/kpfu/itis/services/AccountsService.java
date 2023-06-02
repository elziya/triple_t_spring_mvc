package ru.kpfu.itis.services;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kpfu.itis.dto.response.ProfileDto;

public interface AccountsService {

    ProfileDto getProfileByEmail(UserDetails userDetails);
}
