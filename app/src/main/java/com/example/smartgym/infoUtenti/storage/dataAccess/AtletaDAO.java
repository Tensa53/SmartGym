package com.example.smartgym.infoUtenti.storage.dataAccess;

import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AtletaDAO {

    FirebaseFirestore dbHelper;

    public AtletaDAO() {
        dbHelper = FirebaseFirestore.getInstance();
    }

    public Task<DocumentSnapshot> doRetrieveAthleteDocById(String id) {
        Task<DocumentSnapshot> task = dbHelper.collection("atleti").document(id).get();

        return task;
    }

    public Task<Void> doSaveAthlete(Atleta atleta, String id) {
        Task<Void> task = dbHelper.collection("atleti").document(id).set(atleta);
        return task;
    }

    public void doDeleteAthlete(String id) {
        dbHelper.collection("atleti").document(id).delete();

        return;
    }

    public Task<Void> doUpdateAthlete(Atleta atleta, String id){
        DocumentReference doc = dbHelper.collection("atleti").document(id);
        Task<Void> task = doc.set(atleta);
        return task;
    }

}
