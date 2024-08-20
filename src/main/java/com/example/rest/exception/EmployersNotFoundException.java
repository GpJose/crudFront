package com.example.rest.exception;

public class EmployersNotFoundException extends NullPointerException {

    public EmployersNotFoundException(Long id) {
        super("User with id: " + id + " not found");
    }
    public EmployersNotFoundException() {
        super("Users not found");
    }
}
