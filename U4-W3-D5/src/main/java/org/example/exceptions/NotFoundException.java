package org.example.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(long id) {
        super("The record with id " + id + " not found!");
    }
}
