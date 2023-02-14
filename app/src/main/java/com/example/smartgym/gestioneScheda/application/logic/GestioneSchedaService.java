package com.example.smartgym.gestioneScheda.application.logic;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public interface GestioneSchedaService {

    Task<Void> saveScheda(Map<String, Object> scheda);

    Task<Void> updateScheda(Map<String, Object> scheda, String schedaId);

    Task<DocumentSnapshot> getSchedaById(String schedaId);

    Task<QuerySnapshot> getAllUserSchede(String userId);

    Task<QuerySnapshot> getAllEsercizi();

    Task<QuerySnapshot> getSchedaInUso(String userId);
}
