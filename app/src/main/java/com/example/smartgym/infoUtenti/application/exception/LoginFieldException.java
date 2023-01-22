package com.example.smartgym.infoUtenti.application.exception;

public class LoginFieldException extends Exception{

    public LoginFieldException() {
        super("Inserire il campo nel formato corretto !!!");
    }

    public LoginFieldException(String message) {
        super(message);
    }
}
