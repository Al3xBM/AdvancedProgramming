package com.gomuki.gomuki.demo.service;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// am facut doar pentru not found
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Error> handleNotFoundException(NotFoundException e){
        Error error = new Error("NOT_FOUND_ERROR");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
