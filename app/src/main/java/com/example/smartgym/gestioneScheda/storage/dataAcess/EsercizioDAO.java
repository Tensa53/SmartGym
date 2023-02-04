package com.example.smartgym.gestioneScheda.storage.dataAcess;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class EsercizioDAO {

    FirebaseFirestore dbHelper;

    public EsercizioDAO() {
        dbHelper = FirebaseFirestore.getInstance();
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

    public Task<QuerySnapshot> doRetrieveEsercizioById2(String id) {
        Task<QuerySnapshot> task = dbHelper.collection("esercizi").whereEqualTo("id",id).get();

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

}
