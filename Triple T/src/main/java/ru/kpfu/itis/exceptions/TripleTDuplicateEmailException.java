package ru.kpfu.itis.exceptions;

public class TripleTDuplicateEmailException extends TripleTRestDuplicateEntityException{

    public TripleTDuplicateEmailException() {
        super("Пользователь с таким email уже существует!");
    }
}
