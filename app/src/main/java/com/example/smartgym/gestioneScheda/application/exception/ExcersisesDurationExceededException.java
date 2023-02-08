package com.example.smartgym.gestioneScheda.application.exception;

public class ExcersisesDurationExceededException extends Exception{

    public ExcersisesDurationExceededException() {super("Il valore di durata di un esercizio deve essere compreso tra 20 e 60 secondi");}

    public ExcersisesDurationExceededException(String message) { super(message); }
}
