package com.cyberdev.lumpas.service;

public class OompaLoompaException extends Exception {

    public OompaLoompaException(String message){
        super(message);
    }

    public OompaLoompaException(String message,Throwable error){
        super(message,error);
    }
}
