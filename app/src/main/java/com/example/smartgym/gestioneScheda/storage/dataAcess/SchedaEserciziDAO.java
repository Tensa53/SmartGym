package com.example.smartgym.gestioneScheda.storage.dataAcess;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

/**
 * Questa classe gestisce l'accesso al database per le schede di esercizi.
 */
public class SchedaEserciziDAO {

    FirebaseFirestore dbHelper;

    /**
     * Costruttore della classe. Inizializza il riferimento al database.
     */
    public SchedaEserciziDAO() {
        dbHelper = FirebaseFirestore.getInstance();
    }

    /**
     * Salva una nuova scheda di esercizi nel database.
     *
     * @param scheda la scheda di esercizi da salvare
     * @return un'istanza di Task<Void> che rappresenta l'operazione asincrona di salvataggio della scheda
     */
    public Task<Void> doSaveScheda(Map<String, Object> scheda) {
        DocumentReference dr = dbHelper.collection("schede_esercizi").document();

        DocumentReference drAtleta = dbHelper.document(String.valueOf(scheda.get("ricevente")));

        ArrayList<String> dettagli = (ArrayList<String>) scheda.get("esercizi_scelti");

        ArrayList<DocumentReference> refEsercizi = new ArrayList<>();

        for (String s : dettagli) {
            refEsercizi.add(dbHelper.document(s));
        }

        scheda.put("esercizi_scelti", refEsercizi);
        scheda.put("ricevente", drAtleta);

        return dr.set(scheda);

    }

    /**
     * Recupera tutte le schede di esercizi relative a un dato atleta.
     *
     * @param id l'ID dell'atleta
     * @return un'istanza di Task<QuerySnapshot> che rappresenta l'operazione asincrona di recupero delle schede
     */
    public Task<QuerySnapshot> doRetrieveAllSchedeByUserId(String id) {
        DocumentReference dr = dbHelper.collection("atleti").document(id);

        Log.d("DEBUG", dr.getPath());

        Task<QuerySnapshot> task = dbHelper.collection("schede_esercizi").whereEqualTo("ricevente", dr).get();

        return task;
    }

    /**
     * Aggiorna una scheda di esercizi nel database.
     *
     * @param scheda   la scheda di esercizi aggiornata
     * @param idScheda l'ID della scheda di esercizi da aggiornare
     * @return un'istanza di Task<Void> che rappresenta l'operazione asincrona di aggiornamento della scheda
     */
    public Task<Void> doUpdateSchedaById(Map<String, Object> scheda, String idScheda) {
        DocumentReference dr = dbHelper.collection("schede_esercizi").document(idScheda);

        return dr.set(scheda);
    }

    /**
     * Aggiorna lo stato di utilizzo di una scheda di esercizi nel database.
     *
     * @param idScheda l'ID della scheda di esercizi da aggiornare
     * @param key      la chiave dell'attributo da aggiornare
     * @param value    il nuovo valore dell'attributo
     * @return un'istanza di Task<Void> che rappresenta l'operazione asincrona di aggiornamento della scheda
     */
    public Task<Void> doUpdateSchedaInUso(String idScheda, String key, Boolean value) {
        DocumentReference dr = dbHelper.collection("schede_esercizi").document(idScheda);

        return dr.update(key, value);
    }

    /**
     * Restituisce la scheda esercizi identificata dal parametro id.
     *
     * @param id l'ID della scheda esercizi da recuperare
     * @return la scheda esercizi identificata dal parametro id
     */
    public Task<DocumentSnapshot> doRetrieveSchedaByDocumentId(String id) {
        Task<DocumentSnapshot> task = dbHelper.collection("schede_esercizi").document(id).get();

        return task;
    }

    /**
     * Restituisce una lista di tutte le schede esercizi in uso dall'atleta identificato dal parametro id.
     *
     * @param id l'ID dell'atleta di cui recuperare le schede in uso
     * @return una lista di tutte le schede esercizi in uso dall'atleta identificato dal parametro id
     */
    public Task<QuerySnapshot> doRetrieveSchedaInUso(String id) {
        DocumentReference dr = dbHelper.collection("atleti").document(id);

        Task<QuerySnapshot> task = dbHelper.collection("schede_esercizi").whereEqualTo("ricevente", dr).whereEqualTo("inUso", true).get();

        return task;
    }
}
