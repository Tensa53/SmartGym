package com.example.smartgym.infoUtenti.storage.dataAccess;

import com.example.smartgym.infoUtenti.storage.entity.Utente;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class UtenteDAO {

    FirebaseFirestore dbHelper;

    public UtenteDAO() {
    }

    public Task<DocumentSnapshot> doRetrieveUserDocById(String id) {

        dbHelper = FirebaseFirestore.getInstance(); //ottengo l'istanza di connessione al db

        DocumentReference docRef = dbHelper.collection("utenti").document(id);

        Task<DocumentSnapshot> task = docRef.get();

        return task;

    }

    public Task<QuerySnapshot> doRetrieveUserDocByEmail(String email) {

        dbHelper = FirebaseFirestore.getInstance();

        Query query = dbHelper.collection("utenti").whereEqualTo("mail",email);

        Task<QuerySnapshot> task = query.get();

        return task;
    }

    public Task<QuerySnapshot> doRetrieveUserDocByUsername(String username) {

        dbHelper = FirebaseFirestore.getInstance();

        Query query = dbHelper.collection("utenti").whereEqualTo("username",username);

        Task<QuerySnapshot> task = query.get();

        return task;
    }

    public Task<DocumentReference> doSaveUser(Utente utente) {

        dbHelper = FirebaseFirestore.getInstance();

        Task<DocumentReference> task = dbHelper.collection("utenti").add(utente);

        return task;
    }

}
