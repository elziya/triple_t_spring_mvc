package ru.kpfu.itis.exceptions;

public class TripleTTaskNotFoundException extends TripleTNotFoundException{

    public TripleTTaskNotFoundException() {
        super("Task not found");
    }
}
