package com.aleshkacd.exception;

import com.aleshkacd.booking.client.exception.BookingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MovieShowExceptionHandler {
    @ExceptionHandler(value = {BookingException.class, MovieShowException.class, RemoteServiceException.class})
    public ResponseEntity<Object> handle(RuntimeException exception){
        return new ResponseEntity<>(new ErrorMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
