package ru.kpfu.itis.exceptions;

public class TripleTTagRestNotFoundException extends TripleTRestNotFoundException{

    public TripleTTagRestNotFoundException() {
        super("Tag not found");
    }
}
