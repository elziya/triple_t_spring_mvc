package ru.kpfu.itis.exceptions;

public class TripleTAccountNotFoundException extends TripleTNotFoundException{

    public TripleTAccountNotFoundException() {
        super("Account not found");
    }
}
