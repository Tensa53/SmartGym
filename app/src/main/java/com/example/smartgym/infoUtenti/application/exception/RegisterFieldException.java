package com.example.smartgym.infoUtenti.application.exception;

import java.io.IOException;

public class RegisterFieldException extends Exception {

    public RegisterFieldException() {
        super("Inserire il campo nel formato corretto!!!");
    }

    public RegisterFieldException(String message) {
        super(message);
    }
}
