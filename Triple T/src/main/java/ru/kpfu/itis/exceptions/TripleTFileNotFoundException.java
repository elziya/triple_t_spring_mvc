package ru.kpfu.itis.exceptions;

public class TripleTFileNotFoundException extends TripleTNotFoundException{

    public TripleTFileNotFoundException() {
        super("File not found");
    }
}
