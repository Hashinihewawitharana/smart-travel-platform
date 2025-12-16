package com.smarttravel.bookingservice.exception;


public class HotelNotAvailableException extends RuntimeException {
    public HotelNotAvailableException(String message) {
        super(message);
    }
}

