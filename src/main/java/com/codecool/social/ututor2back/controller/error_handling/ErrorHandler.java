package com.codecool.social.ututor2back.controller.error_handling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDro handleBadRequest(MethodArgumentNotValidException e){
        String errMsg = e.getAllErrors().stream()
                .map(ex -> ex.getDefaultMessage())
                .collect(Collectors.joining(" | "));

        return new ErrorResponseDro(errMsg);
    }
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
    public record ErrorResponseDro(String info){}
}
