package ru.kpfu.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dto.response.ProfileDto;
import ru.kpfu.itis.exceptions.TripleTAccountNotFoundException;
import ru.kpfu.itis.repositories.AccountRepository;
import ru.kpfu.itis.services.AccountsService;

import static ru.kpfu.itis.dto.response.ProfileDto.from;

@RequiredArgsConstructor
@Service
public class AccountsServiceImpl implements AccountsService {

    private final AccountRepository accountRepository;

    @Override
    public ProfileDto getProfileByEmail(UserDetails userDetails) {
        return from(accountRepository.findByEmail(userDetails.getUsername()).orElseThrow(TripleTAccountNotFoundException::new));
    }
}
