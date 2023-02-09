package com.example.smartgym.gestioneScheda.application.logic;

import com.example.smartgym.gestioneScheda.storage.entity.SchedaEsercizi;

public class GestioneSchedaServiceImpl implements GestioneSchedaService {

    SchedaLogic schedaLogic;

    @Override
    public void saveScheda(SchedaEsercizi scheda) {
        schedaLogic.saveScheda(scheda);
        return;
    }

}
