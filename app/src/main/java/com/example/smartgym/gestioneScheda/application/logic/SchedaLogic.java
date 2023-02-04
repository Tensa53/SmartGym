package com.example.smartgym.gestioneScheda.application.logic;

import com.example.smartgym.gestioneScheda.storage.dataAcess.SchedaEserciziDAO;
import com.example.smartgym.gestioneScheda.storage.entity.SchedaEsercizi;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class SchedaLogic {

    private FirebaseAuth mauth;
    private SchedaEserciziDAO schedaEserciziDAO;

    public SchedaLogic(){
        mauth = FirebaseAuth.getInstance();
        schedaEserciziDAO = new SchedaEserciziDAO();
    }

    public Task<Void> saveScheda(SchedaEsercizi schedaEsercizi) {
        return schedaEserciziDAO.doSaveScheda(schedaEsercizi);
    }

}
