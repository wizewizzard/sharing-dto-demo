package com.aleshkacd.booking.client.exception;

public class BookingServiceException extends RuntimeException{
    public BookingServiceException(String message) {
        super(message);
    }

    public BookingServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
