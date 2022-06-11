package com.aleshkacd.exception;

import com.aleshkacd.booking.client.exception.BookingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookingExceptionHandler {
    @ExceptionHandler(value = {BookingException.class})
    public ResponseEntity<BookingException> handle(BookingException exception){
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}
