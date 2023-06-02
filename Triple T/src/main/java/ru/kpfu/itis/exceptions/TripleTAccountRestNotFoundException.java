package ru.kpfu.itis.exceptions;

public class TripleTAccountRestNotFoundException extends TripleTRestNotFoundException{

    public TripleTAccountRestNotFoundException() {
        super("Account not found");
    }
}
