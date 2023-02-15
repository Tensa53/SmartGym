package com.example.smartgym.gestioneScheda.application.exception;

/**
 * Eccezione lanciata quando il numero di esercizi selezionati è superiore a 10 o inferiore a 3.
 */
public class NumberExercsisesExceededException extends Exception{

    /**
     * Costruttore senza argomenti, imposta un messaggio di default.
     */
    public NumberExercsisesExceededException() {
        super("Scegliere un numero di esercizi valido, compreso tra 3 e 10");
    }

    /**
     * Costruttore con messaggio personalizzato.
     * @param message il messaggio da visualizzare.
     */
    public NumberExercsisesExceededException(String message) {
        super(message);
    }
}
