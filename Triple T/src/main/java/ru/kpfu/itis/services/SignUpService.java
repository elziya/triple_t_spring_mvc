package ru.kpfu.itis.services;

import ru.kpfu.itis.dto.request.SignUpForm;
import ru.kpfu.itis.exceptions.TripleTDuplicateEmailException;

public interface SignUpService {

    void signUp(SignUpForm form) throws TripleTDuplicateEmailException;

}
