package ru.kpfu.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dto.request.SignUpForm;
import ru.kpfu.itis.exceptions.TripleTDuplicateEmailException;
import ru.kpfu.itis.models.Account;
import ru.kpfu.itis.repositories.AccountRepository;
import ru.kpfu.itis.services.SignUpService;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpForm form) throws TripleTDuplicateEmailException {

        if (accountRepository.findByEmail(form.getEmail()).isPresent()) {
            throw new TripleTDuplicateEmailException();
        }

        Account account = Account.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .passwordHash(passwordEncoder.encode(form.getPassword()))
                .role(Account.Role.USER)
                .build();

        accountRepository.save(account);
    }
}
