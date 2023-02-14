package com.example.smartgym.gestioneScheda.application.logic;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class GestioneSchedaServiceImpl implements GestioneSchedaService {

    SchedaLogic schedaLogic;

    public GestioneSchedaServiceImpl() {
        schedaLogic = new SchedaLogic();
    }

    @Override
    public Task<Void> saveScheda(Map<String, Object> scheda) {
        return schedaLogic.saveScheda(scheda);
    }

    @Override
    public Task<Void> updateScheda(Map<String, Object> scheda, String schedaId) {
        return null;
    }

    @Override
    public Task<DocumentSnapshot> getSchedaById(String schedaId) {
        return schedaLogic.getSchedaById(schedaId);
    }

    @Override
    public Task<QuerySnapshot> getAllUserSchede(String userId) {
        return schedaLogic.getAllUserSchede(userId);
    }

    @Override
    public Task<QuerySnapshot> getAllEsercizi() {
        return schedaLogic.getAllEsercizi();
    }

    @Override
    public Task<QuerySnapshot> getSchedaInUso(String userId) {
        return schedaLogic.getSchedaInUso(userId);
    }
}
