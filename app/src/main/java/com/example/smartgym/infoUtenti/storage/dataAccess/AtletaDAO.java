package com.example.smartgym.infoUtenti.storage.dataAccess;

import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class AtletaDAO {

    FirebaseFirestore dbHelper;

    public AtletaDAO() {
        dbHelper = FirebaseFirestore.getInstance();
    }

    public Task<DocumentSnapshot> doRetrieveAthleteDocByEmail(String email) {
        Task<DocumentSnapshot> task = dbHelper.collection("atleti").document(email).get();

        return task;
    }

    public Task<Void> doSaveAthlete(Atleta atleta) {
        Task<Void> task = dbHelper.collection("atleti").document(atleta.getEmail()).set(atleta);

        return task;
    }

    public void doDeleteAthlete(String email) {
        dbHelper.collection("atleti").document(email).delete();

        return;
    }

}
