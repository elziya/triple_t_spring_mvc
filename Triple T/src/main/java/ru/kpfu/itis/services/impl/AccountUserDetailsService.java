package ru.kpfu.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.repositories.AccountRepository;
import ru.kpfu.itis.security.details.AccountUserDetails;

@RequiredArgsConstructor
@Service
public class AccountUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new AccountUserDetails(
                accountRepository.findByEmail(email)
                        .orElseThrow(
                                () -> new UsernameNotFoundException("User not found")));
    }
}
