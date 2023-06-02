package ru.kpfu.itis.exceptions;

public class TripleTDuplicateTagException extends TripleTRestDuplicateEntityException{

    public TripleTDuplicateTagException() {
        super("Такой тег уже существует");
    }
}
