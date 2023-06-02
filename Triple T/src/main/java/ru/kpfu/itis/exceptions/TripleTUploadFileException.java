package ru.kpfu.itis.exceptions;

public class TripleTUploadFileException extends TripleTFileException{

    public TripleTUploadFileException() {
        super("Can't upload file");
    }
}
