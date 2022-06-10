package com.aleshkacd.exception;

public class MovieShowException extends RuntimeException{
    public MovieShowException(String message) {
        super(message);
    }

    public MovieShowException(String message, Throwable cause) {
        super(message, cause);
    }
}
