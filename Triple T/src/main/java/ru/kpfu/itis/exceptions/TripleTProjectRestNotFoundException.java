package ru.kpfu.itis.exceptions;

public class TripleTProjectRestNotFoundException extends TripleTRestNotFoundException {

    public TripleTProjectRestNotFoundException() {
        super("Project not found");
    }
}
