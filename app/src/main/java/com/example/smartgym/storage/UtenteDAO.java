package com.example.smartgym.storage;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UtenteDAO {

    FirebaseFirestore dbHelper;

    public UtenteDAO() {
    }

    public DocumentReference doRetrieveUserDocById(String id) {

        dbHelper = FirebaseFirestore.getInstance(); //ottengo l'istanza di connessione al db

        DocumentReference docRef = dbHelper.collection("utenti").document(id); //viene prelevato il riferimento al documento attraverso il suo id

        return docRef;

    }

}
