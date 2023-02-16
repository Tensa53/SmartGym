package com.example.smartgym.infoUtenti.application.exception;

/**
 * Eccezione lanciata quando uno dei campi del form di inserimento e/o modifica carattertistiche
 * dell'atleta non è valido
 */
public class AthleteFeaturesFieldException extends Exception{

    /**
     * Costruttore senza argomenti, imposta un messaggio di default.
     */
    public AthleteFeaturesFieldException() {super("Inserire il campo nel formato corretto !!!");}

    /**
     * Costruttore con messaggio personalizzato.
     * @param message il messaggio da visualizzare.
     */
    public AthleteFeaturesFieldException(String message) {super(message);}
}
