package ru.kpfu.itis.exceptions;

public class TripleTDownloadFileException extends TripleTFileException{

    public TripleTDownloadFileException() {
        super("Can't get file");
    }
}
