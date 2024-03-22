package org.example.exceptions;

public class NotFoundIsbnException extends RuntimeException{
    public NotFoundIsbnException(String id) {
        super("The record with id " + id + " not found!");
    }
}
