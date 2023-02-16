package com.example.smartgym.infoUtenti.application.exception;

/**
 * Eccezione lanciata quando uno dei campi del form di login non è valido
 */
public class LoginFieldException extends Exception{

    /**
     * Costruttore senza argomenti, imposta un messaggio di default.
     */
    public LoginFieldException() {
        super("Inserire il campo nel formato corretto !!!");
    }

    /**
     * Costruttore con messaggio personalizzato.
     * @param message il messaggio da visualizzare.
     */
    public LoginFieldException(String message) {
        super(message);
    }
}
