package com.example.smartgym.gestioneScheda.application.logic;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

/**
 * Implementazione dell'interfaccia GestioneSchedaService che fornisce i metodi per la gestione delle schede
 * di allenamento dell'utente.
 */
public class GestioneSchedaServiceImpl implements GestioneSchedaService {

    SchedaLogic schedaLogic;

    /**
     * Costruttore della classe che inizializza un'istanza di SchedaLogic per effettuare le operazioni
     * di accesso ai dati relativi alle schede di allenamento.
     */
    public GestioneSchedaServiceImpl() {
        schedaLogic = new SchedaLogic();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task<Void> saveScheda(Map<String, Object> scheda) {
        return schedaLogic.saveScheda(scheda);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task<Void> updateScheda(Map<String, Object> scheda, String schedaId) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task<DocumentSnapshot> getSchedaById(String schedaId) {
        return schedaLogic.getSchedaById(schedaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task<QuerySnapshot> getAllUserSchede(String userId) {
        return schedaLogic.getAllUserSchede(userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task<QuerySnapshot> getAllEsercizi() {
        return schedaLogic.getAllEsercizi();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task<QuerySnapshot> getSchedaInUso(String userId) {
        return schedaLogic.getSchedaInUso(userId);
    }
}
