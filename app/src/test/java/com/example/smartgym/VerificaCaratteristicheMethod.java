package com.example.smartgym;

import com.example.smartgym.infoUtenti.application.exception.AthleteFeaturesFieldException;
import com.example.smartgym.utils.FormUtils;

public class VerificaCaratteristicheMethod {

    public boolean verificaCaratteristiche(Integer peso, Integer altezza, Integer numeroAllenamenti) {
        FormUtils formUtils = new FormUtils();

        try {
            formUtils.controllaCaratteristicheAtleta(peso, altezza, numeroAllenamenti);
        } catch (AthleteFeaturesFieldException e) {
            return false;
        }

        return true;
    }

}
