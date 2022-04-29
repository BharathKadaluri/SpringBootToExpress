package com.nineleaps.expresssample.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
public class NotFoundException extends RuntimeException{
    private String message;
    public NotFoundException(String message){
        super(message);
        this.message = message;
    }
}
