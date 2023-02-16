package com.example.smartgym.infoUtenti.storage.dataAccess;

import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Questa classe rappresenta un oggetto per l'accesso ai dati degli esercizi nella base di dati Firebase Firestore.
 */
public class AtletaDAO {

    FirebaseFirestore dbHelper;

    /**
     * Costruttore della classe. Inizializza l'istanza del dbHelper
     */
    public AtletaDAO() {
        dbHelper = FirebaseFirestore.getInstance();
    }

    /**
     * Recupera il documento dell'atleta attraverso l'id
     *
     * @param id, l'id del documento
     * @return Task<DocumentSnapshot> rappresenta il risultato dell'operazione
     */
    public Task<DocumentSnapshot> doRetrieveAthleteDocById(String id) {
        Task<DocumentSnapshot> task = dbHelper.collection("atleti").document(id).get();

        return task;
    }

    /**
     * Salva l'atleta nel db
     *
     * @param atleta, l'atleta da salvare
     * @param id, l'id del documento
     * @return Task<Void> rappresenta il risultato dell'operazione
     */
    public Task<Void> doSaveAthlete(Atleta atleta, String id) {
        Task<Void> task = dbHelper.collection("atleti").document(id).set(atleta);
        return task;
    }

    /**
     * Cancella l'atleta dal db
     *
     * @param id, l'id del documento
     * @return Task<Void> rappresenta il risultato dell'operazione
     */
    public Task<Void> doDeleteAthlete(String id) {
        return dbHelper.collection("atleti").document(id).delete();
    }

    /**
     * Aggiorna l'atleta
     *
     * @param atleta, l'atleta aggiornato
     * @param id, l'id del documento da modificare
     * @return Task<Void> rappresenta il risultato dell'operazione
     */
    public Task<Void> doUpdateAthlete(Atleta atleta, String id){
        DocumentReference doc = dbHelper.collection("atleti").document(id);
        Task<Void> task = doc.set(atleta);
        return task;
    }

}
