package com.aleshkacd.exception;

import com.aleshkacd.booking.client.error.BookingServiceError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookingExceptionHandler {
    @ExceptionHandler(value = {BookingException.class})
    public ResponseEntity<BookingServiceError> handle(BookingException exception){
        return new ResponseEntity<>(new BookingServiceError(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
