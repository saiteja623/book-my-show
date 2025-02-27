package com.example.bookMyShow.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BookingFailedException.class)
    public ResponseEntity<String> handleBookingFailed(BookingFailedException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
