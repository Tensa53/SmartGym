package com.example.smartgym.gestioneScheda.storage.dataAcess;

import android.util.Log;

import com.example.smartgym.gestioneScheda.storage.entity.SchedaEsercizi;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class SchedaEserciziDAO {

    FirebaseFirestore dbHelper;

    public SchedaEserciziDAO() {
        dbHelper = FirebaseFirestore.getInstance();
    }

    public Task<QuerySnapshot> doRetrieveAllSchedeByUserEmail(String email) {
        DocumentReference dr = dbHelper.collection("atleti").document(email);

        Task<QuerySnapshot> task = dbHelper.collection("schede_esercizi").whereEqualTo("ricevente",dr).get();

        return task;
    }

    public Task<DocumentSnapshot> doRetrieveSchedaByDocumentId(String id) {
        Task<DocumentSnapshot> task = dbHelper.collection("schede_esercizi").document(id).get();

        return task;
    }
}
