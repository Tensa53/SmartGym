package com.example.smartgym.gestioneScheda.application.exception;

public class ExercisesRepsExceededException extends Exception{

    public ExercisesRepsExceededException() { super("Il numero di ripetizioni di un esercizio deve essere compreso tra 3 e 30"); }

    public ExercisesRepsExceededException(String message) {
        super(message);
    }

}
