package com.springboot.gym.gymsystem.exceptions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
        .error("Bad Requests")
        .message("Tipo de dato incorrecto para el parámetro")
        .details(webRequest.getDescription(false))
        .build();

        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest webRequest){

        Map<String,String> mapErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String clave = ((FieldError) error).getField();
            String valor = error.getDefaultMessage();

            mapErrors.put(clave, valor);
        });

        ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse.builder()
        .timeStamp(Instant.now())
        .error(mapErrors.toString())
        .message("Hubo un error al completar un campo")
        .details(webRequest.getDescription(false))
        .build();

        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiExceptionResponse> handleInvalidEnumException(HttpMessageNotReadableException ex, WebRequest webRequest) {
   
        String defaultMessage = "Hay un valor proporcionado no es válido.";
        String exMessage = ex.getMessage();
        String customMessage;

        //Personifico el mensaje segun el enum sobre el que se esta trabajando
        if (exMessage.contains("DaysEnum")) {
            customMessage = "Debes elegir un día válido (MONDAY, TUESDAY, ..., SUNDAY)";
        } else if (exMessage.contains("MembershipTypeEnum")) {
            customMessage = "Debes elegir un tipo de membresía existente (MONTHLY, QUARTERLY, ANNUAL)";
        } else {
            customMessage = defaultMessage;
        }


    ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse.builder()
        .timeStamp(Instant.now())
        .error("Bad Request")
        .message(customMessage)
        .details(webRequest.getDescription(false))
        .build();
   
    return new ResponseEntity<>(apiExceptionResponse,HttpStatus.BAD_REQUEST);
    }

}
