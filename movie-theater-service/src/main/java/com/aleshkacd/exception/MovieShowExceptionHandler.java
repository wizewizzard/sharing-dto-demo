package com.aleshkacd.exception;

import com.aleshkacd.booking.client.exception.BookingServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MovieShowExceptionHandler {
    @ExceptionHandler(value = {BookingServiceException.class, MovieShowException.class, RemoteServiceException.class})
    public ResponseEntity<Object> handle(RuntimeException exception){
        return new ResponseEntity<>(new ErrorMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
