package com.example.demo.handlers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.StringJoiner;

@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    String handleConstraint(ConstraintViolationException e) {
        StringJoiner joiner = new StringJoiner("\n", "", "");
        joiner.add("Error: ");
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            String s = violation.getMessage();
            joiner.add(s);
        }
        return joiner.toString();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    String handleConstraint(IllegalArgumentException e) {
        return e.getMessage();
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    String handleNotReadable(HttpMessageNotReadableException e) {
        return "There was a problem with your input. Please try again.";
    }
}
