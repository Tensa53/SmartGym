package com.example.smartgym.gestioneScheda.application.logic;

import com.example.smartgym.gestioneScheda.storage.dataAcess.EsercizioDAO;
import com.example.smartgym.gestioneScheda.storage.dataAcess.SchedaEserciziDAO;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class SchedaLogic {

    private FirebaseAuth mauth;
    private SchedaEserciziDAO schedaEserciziDAO;
    private EsercizioDAO esercizioDAO;

    public SchedaLogic(){
        mauth = FirebaseAuth.getInstance();
        schedaEserciziDAO = new SchedaEserciziDAO();
        esercizioDAO = new EsercizioDAO();
    }

    public Task<Void> saveScheda(Map<String, Object> scheda) {
        return schedaEserciziDAO.doSaveScheda(scheda);
    }

    public Task<Void> updateScheda(Map<String, Object> scheda, String schedaId) {
        return schedaEserciziDAO.doUpdateSchedaById(scheda, schedaId);
    }

    public Task<DocumentSnapshot> getSchedaById(String schedaId) {
        return schedaEserciziDAO.doRetrieveSchedaByDocumentId(schedaId);
    }

    public Task<QuerySnapshot> getSchedaInUso(String userId) {
        return schedaEserciziDAO.doRetrieveSchedaInUso(userId);
    }

    public Task<QuerySnapshot> getAllUserSchede(String userId) {
        return schedaEserciziDAO.doRetrieveAllSchedeByUserId(userId);
    }

    public Task<Void> setSchedaInUso(String idNuovaSchedaInuso, String inUso, boolean b) {
        return schedaEserciziDAO.doUpdateSchedaInUso(idNuovaSchedaInuso, inUso, b);
    }

    public String saveDettaglioEsercizio(Map<String, Object> dettagli) {
        return esercizioDAO.doSaveDettaglioEsercizio(dettagli);
    }

    public Task<QuerySnapshot> getAllEsercizi() {
        return esercizioDAO.doRetrieveAllEsercizi();
    }

    public Task<QuerySnapshot> getAllEserciziParteDelCorpo(String parteDelCorpo) {
        return esercizioDAO.doRetrieveEsercizioByParteDelCorpo(parteDelCorpo);
    }
}
