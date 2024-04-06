package com.moviepiracy.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("A user with the email " + email + " already exists.");
    }
}
