package ru.kpfu.itis.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.models.Account;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByEmail(String email);
}
