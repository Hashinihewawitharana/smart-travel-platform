package com.smarttravel.bookingservice.exception;


public class FlightNotAvailableException extends RuntimeException {
    public FlightNotAvailableException(String message) {
        super(message);
    }
}


