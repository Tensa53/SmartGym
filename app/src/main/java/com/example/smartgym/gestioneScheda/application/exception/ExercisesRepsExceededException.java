package com.example.smartgym.gestioneScheda.application.exception;

/**
 * Eccezione lanciata quando il numero di ripetizioni di un esercizio è superiore a 30 o inferiore a 3.
 */
public class ExercisesRepsExceededException extends Exception{

    /**
     * Costruttore senza argomenti, imposta un messaggio di default.
     */
    public ExercisesRepsExceededException() { super("Il numero di ripetizioni di un esercizio deve essere compreso tra 3 e 30"); }

    /**
     * Costruttore con messaggio personalizzato.
     * @param message il messaggio da visualizzare.
     */
    public ExercisesRepsExceededException(String message) {
        super(message);
    }

}
