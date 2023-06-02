package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dto.oAuth.VkAccessTokenDto;
import ru.kpfu.itis.dto.oAuth.VkAccountDto;
import ru.kpfu.itis.exceptions.TripleTVkOAuthException;
import ru.kpfu.itis.models.Account;
import ru.kpfu.itis.repositories.AccountRepository;
import ru.kpfu.itis.services.OAuthService;
import ru.kpfu.itis.utils.VkOAuthUtility;

import java.util.Optional;

@Service
public class OAuthServiceImpl implements OAuthService {

    private final AccountRepository accountRepository;
    private final VkOAuthUtility vkUtility;

    @Autowired
    public OAuthServiceImpl(AccountRepository accountRepository, VkOAuthUtility vkUtility) {
        this.accountRepository = accountRepository;
        this.vkUtility = vkUtility;
    }

    @Override
    public Account auth(String code) throws TripleTVkOAuthException {

        VkAccessTokenDto token = vkUtility.getAccessToken(code);

        if (token.getEmail() != null) {
            Optional<Account> accountOptional = accountRepository.findByEmail(token.getEmail());

            if (accountOptional.isPresent()) {
                return accountOptional.get();
            }

            VkAccountDto vkUserDto = vkUtility.getAccount(token);
            Account account = Account.builder()
                    .firstName(vkUserDto.getFirstName())
                    .lastName(vkUserDto.getLastName())
                    .email(token.getEmail())
                    .role(Account.Role.USER)
                    .build();

            return accountRepository.save(account);
        }

        throw new TripleTVkOAuthException();
    }
}
