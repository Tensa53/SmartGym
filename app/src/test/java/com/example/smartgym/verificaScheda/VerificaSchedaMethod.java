package com.example.smartgym.verificaScheda;

import com.example.smartgym.gestioneScheda.application.exception.ExcersisesDurationExceededException;
import com.example.smartgym.gestioneScheda.application.exception.ExercisesRepsExceededException;
import com.example.smartgym.gestioneScheda.application.exception.NumberExercsisesExceededException;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;
import com.example.smartgym.utils.FormUtils;

import java.util.ArrayList;

public class VerificaSchedaMethod {

    public boolean verificaScheda(ArrayList<Esercizio> checked) {
        FormUtils formUtils = new FormUtils();

        try {
            formUtils.controllaListaEsercizi(checked);
        } catch (NumberExercsisesExceededException | ExcersisesDurationExceededException | ExercisesRepsExceededException e) {
            return false;
        }

        return true;
    }

}
