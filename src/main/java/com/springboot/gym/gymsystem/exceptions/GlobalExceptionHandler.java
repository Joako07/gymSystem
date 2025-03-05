package com.springboot.gym.gymsystem.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiErrorException.class)
    public ResponseEntity<ApiExceptionResponse> handleApiErrorException(ApiErrorException ex, WebRequest webRequest ){
        ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse.builder()
        .timeStamp(Instant.now())
        .error(ex.getError())
        .message(ex.getMessage())
        .details(webRequest.getDescription(false))
        .build();

        return new ResponseEntity<>(apiExceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundApiException.class)
    public ResponseEntity<ApiExceptionResponse> handleNotFoundException(NotFoundApiException ex, WebRequest webRequest){
        ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse.builder()
        .timeStamp(Instant.now())
        .error(ex.getError())
        .message(ex.getMessage())
        .details(webRequest.getDescription(false))
        .build();

        return new ResponseEntity<>(apiExceptionResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestApiException.class)
    public ResponseEntity<ApiExceptionResponse> handleBadRequest(BadRequestApiException ex, WebRequest webRequest){
        ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse.builder()
        .timeStamp(Instant.now())
        .error(ex.getError())
        .message(ex.getMessage())
        .details(webRequest.getDescription(false))
        .build();

        return new ResponseEntity<>(apiExceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiExceptionResponse> handleMethodArgumentType(MethodArgumentTypeMismatchException ex, WebRequest webRequest){

        ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse.builder()
        .timeStamp(Instant.now())
        .error(ex.getError())
        .message(ex.getMessage())
        .details(webRequest.getDescription(false))
        .build();

        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
