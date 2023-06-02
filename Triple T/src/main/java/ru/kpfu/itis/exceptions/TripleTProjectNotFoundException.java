package ru.kpfu.itis.exceptions;

public class TripleTProjectNotFoundException extends TripleTNotFoundException{

    public TripleTProjectNotFoundException() {
        super("Project not found");
    }
}
