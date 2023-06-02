package ru.kpfu.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.exceptions.TripleTVkOAuthException;
import ru.kpfu.itis.security.details.AccountUserDetails;
import ru.kpfu.itis.services.OAuthService;

@RequiredArgsConstructor
@Component("userDetailsVkOAuthService")
public class AccountUserDetailsVkOAuthServiceImpl implements UserDetailsService {

    private final OAuthService oAuthService;

    @Override
    public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {
        return new AccountUserDetails(oAuthService.auth(code));
    }
}
