package com.cyberdev.lumpas.oompaLoompa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OompaLoompaNotFoundException extends RuntimeException {
    public OompaLoompaNotFoundException(String message){
        super(message);
    }
}
