package com.example.smartgym.infoUtenti.application.exception;

/**
 * Eccezione lanciata quando uno dei campi del form di registrazione non è valido
 */
public class RegisterFieldException extends Exception {

    /**
     * Costruttore senza argomenti, imposta un messaggio di default.
     */
    public RegisterFieldException() {
        super("Inserire il campo nel formato corretto!!!");
    }

    /**
     * Costruttore con messaggio personalizzato.
     * @param message il messaggio da visualizzare.
     */
    public RegisterFieldException(String message) {
        super(message);
    }
}
