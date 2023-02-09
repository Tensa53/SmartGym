package com.example.smartgym.gestioneScheda.application.exception;

public class NumberExercsisesExceededException extends Exception{

    public NumberExercsisesExceededException() {
        super("Scegliere un numero di esercizi valido, compreso tra 3 e 10");
    }

    public NumberExercsisesExceededException(String message) {
        super(message);
    }
}
