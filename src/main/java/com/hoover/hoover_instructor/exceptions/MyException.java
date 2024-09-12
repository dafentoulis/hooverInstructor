package com.hoover.hoover_instructor.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class MyException extends RuntimeException{
    private final String message;
    private final HttpStatus httpStatus;

}
