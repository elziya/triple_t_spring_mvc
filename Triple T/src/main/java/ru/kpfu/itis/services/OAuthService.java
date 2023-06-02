package ru.kpfu.itis.services;

import ru.kpfu.itis.models.Account;

public interface OAuthService {

    Account auth(String code);

}
