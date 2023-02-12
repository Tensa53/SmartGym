package com.example.smartgym.gestioneScheda.storage.dataAcess;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class EsercizioDAO {

    FirebaseFirestore dbHelper;

    public EsercizioDAO() {
        dbHelper = FirebaseFirestore.getInstance();
    }

    public Task<QuerySnapshot> doRetrieveAllEsercizi() {
        Log.d("DEBUG","SONO QUIII");

        Task<QuerySnapshot> task = dbHelper.collection("esercizi").get();

        return task;
    }

    public Task<DocumentSnapshot> doRetrieveDettaglioById(String id) {
        Task<DocumentSnapshot> task = dbHelper.collection("dettagli_esercizi").document(id).get();

        return task;
    }

    public Task<DocumentSnapshot> doRetrieveEsercizioById(String id) {
        Task<DocumentSnapshot> task = dbHelper.collection("esercizi").document(id).get();

        return task;
    }

    public Task<QuerySnapshot> doRetrieveEsercizioByParteDelCorpo(String parteDelCorpo) {
        Task<QuerySnapshot> task = dbHelper.collection("esercizi").whereEqualTo("parteDelCorpo",parteDelCorpo).get();

        return task;
    }

    public Task<DocumentSnapshot> doRetrieveByName(String nome) {
        Task<DocumentSnapshot> task = dbHelper.collection("esercizi").document(nome).get();

        return task;
    }

    public Task<QuerySnapshot> doRetrieveAll() {
        Task<QuerySnapshot> task = dbHelper.collection("esercizi").get();

        return task;
    }

    public String doSaveDettaglioEsercizio(Map<String,Object> dettagli) {
        DocumentReference docRef = dbHelper.collection("dettagli_esercizi").document();

        String esercizioPath = (String) dettagli.get("esercizio");

        DocumentReference docRefEsercizio = dbHelper.document(esercizioPath);

        dettagli.put("esercizio", docRefEsercizio);

        String id = docRef.getPath();

        docRef.set(dettagli);

        return id;
    }

}
