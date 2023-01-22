package com.example.smartgym.gestioneScheda.application.exception;

public class NumberExercsisesExceededException extends Exception{

    public NumberExercsisesExceededException() {
        super("Scegliere un numero di esercizi valido");
    }

    public NumberExercsisesExceededException(String message) {
        super(message);
    }
}
