package com.example.smartgym.gestioneScheda.application.logic;

import java.util.Map;

public class GestioneSchedaServiceImpl implements GestioneSchedaService {

    SchedaLogic schedaLogic;


    public GestioneSchedaServiceImpl() {
        schedaLogic = new SchedaLogic();
    }

    @Override
    public void saveScheda(Map<String, Object> scheda) {
        schedaLogic.saveScheda(scheda);
    }
}
