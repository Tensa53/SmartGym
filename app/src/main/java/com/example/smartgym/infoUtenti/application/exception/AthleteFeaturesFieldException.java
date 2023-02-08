package com.example.smartgym.infoUtenti.application.exception;

public class AthleteFeaturesFieldException extends Exception{

    public AthleteFeaturesFieldException() {super("Inserire il campo nel formato corretto !!!");}

    public AthleteFeaturesFieldException(String message) {super(message);}
}
