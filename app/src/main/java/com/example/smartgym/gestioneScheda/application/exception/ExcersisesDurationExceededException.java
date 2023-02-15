package com.example.smartgym.gestioneScheda.application.exception;

/**
 * Eccezione lanciata quando il valore di durata di un esercizio è superiore a 60 secondi o inferiore a 20 secondi.
 */
public class ExcersisesDurationExceededException extends Exception{

    /**
     * Costruttore senza argomenti, imposta un messaggio di default.
     */
    public ExcersisesDurationExceededException() {super("Il valore di durata di un esercizio deve essere compreso tra 20 e 60 secondi");}

    /**
     * Costruttore con messaggio personalizzato.
     * @param message il messaggio da visualizzare.
     */
    public ExcersisesDurationExceededException(String message) { super(message); }
}
