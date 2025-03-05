package com.springboot.gym.gymsystem.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BadRequestApiException extends RuntimeException{

    private final String error;
    private final String message;

    public String getError(){
        return error;
    }

}
