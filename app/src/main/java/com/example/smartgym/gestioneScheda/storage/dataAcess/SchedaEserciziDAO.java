package com.example.smartgym.gestioneScheda.storage.dataAcess;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class SchedaEserciziDAO {

    FirebaseFirestore dbHelper;

    public SchedaEserciziDAO() {
        dbHelper = FirebaseFirestore.getInstance();
    }

    public Task<Void> doSaveScheda(Map<String, Object> scheda) {
        DocumentReference dr = dbHelper.collection("schede_esercizi").document();

        DocumentReference drAtleta = dbHelper.document(String.valueOf(scheda.get("ricevente")));

        ArrayList<String> dettagli = (ArrayList<String>) scheda.get("esercizi_scelti");

        ArrayList<DocumentReference> refEsercizi = new ArrayList<>();

        for (String s : dettagli){
            refEsercizi.add(dbHelper.document(s));
        }

        scheda.put("esercizi_scelti", refEsercizi);
        scheda.put("ricevente", drAtleta);

        return dr.set(scheda);

    }

    public Task<QuerySnapshot> doRetrieveAllSchedeByUserId(String id) {
        DocumentReference dr = dbHelper.collection("atleti").document(id);

        Log.d("DEBUG",dr.getPath());

        Task<QuerySnapshot> task = dbHelper.collection("schede_esercizi").whereEqualTo("ricevente",dr).get();

        return task;
    }


    public Task<Void> doUpdateSchedaInUso(String idScheda, String key, Boolean value) {
        DocumentReference dr = dbHelper.collection("schede_esercizi").document(idScheda);

        return dr.update(key, value);
    }

    public Task<DocumentSnapshot> doRetrieveSchedaByDocumentId(String id) {
        Task<DocumentSnapshot> task = dbHelper.collection("schede_esercizi").document(id).get();

        return task;
    }

    public Task<QuerySnapshot> doRetrieveSchedaInUso(String id) {
        DocumentReference dr = dbHelper.collection("atleti").document(id);

        Task<QuerySnapshot> task = dbHelper.collection("schede_esercizi").whereEqualTo("ricevente", dr).whereEqualTo("inUso", true).get();

        return task;
    }
}
