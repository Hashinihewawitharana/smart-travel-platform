package com.smarttravel.userservice.exception;

/**
 * Custom exception thrown when a User is not found in the database.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
