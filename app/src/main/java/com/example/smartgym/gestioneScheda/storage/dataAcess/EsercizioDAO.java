package com.example.smartgym.gestioneScheda.storage.dataAcess;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

/**
 * Questa classe rappresenta un oggetto per l'accesso ai dati degli esercizi nella base di dati Firebase Firestore.
 */
public class EsercizioDAO {

    FirebaseFirestore dbHelper;

    /**
     * Costruttore della classe EsercizioDAO.
     * Crea un'istanza di FirebaseFirestore per accedere alla base di dati Firestore.
     */
    public EsercizioDAO() {
        dbHelper = FirebaseFirestore.getInstance();
    }

    /**
     * Recupera tutti gli esercizi presenti nella collezione "esercizi" della base di dati Firestore.
     *
     * @return un oggetto Task che rappresenta l'operazione asincrona di recupero di tutti gli esercizi.
     */
    public Task<QuerySnapshot> doRetrieveAllEsercizi() {
        Task<QuerySnapshot> task = dbHelper.collection("esercizi").get();

        return task;
    }

    /**
     * Recupera il dettaglio dell'esercizio corrispondente all'ID specificato.
     *
     * @param id l'ID dell'esercizio di cui recuperare il dettaglio.
     * @return un oggetto Task che rappresenta l'operazione asincrona di recupero del dettaglio dell'esercizio.
     */
    public Task<DocumentSnapshot> doRetrieveDettaglioById(String id) {
        Task<DocumentSnapshot> task = dbHelper.collection("dettagli_esercizi").document(id).get();

        return task;
    }

    /**
     * Recupera l'esercizio corrispondente all'ID specificato.
     *
     * @param id l'ID dell'esercizio di cui recuperare i dati.
     * @return un oggetto Task che rappresenta l'operazione asincrona di recupero dell'esercizio.
     */
    public Task<DocumentSnapshot> doRetrieveEsercizioById(String id) {
        Task<DocumentSnapshot> task = dbHelper.collection("esercizi").document(id).get();

        return task;
    }

    /**
     * Recupera tutti gli esercizi che appartengono alla parte del corpo specificata.
     *
     * @param parteDelCorpo la parte del corpo di cui recuperare gli esercizi.
     * @return un oggetto Task che rappresenta l'operazione asincrona di recupero degli esercizi appartenenti alla parte del corpo specificata.
     */
    public Task<QuerySnapshot> doRetrieveEsercizioByParteDelCorpo(String parteDelCorpo) {
        Task<QuerySnapshot> task = dbHelper.collection("esercizi").whereEqualTo("parteDelCorpo", parteDelCorpo).get();

        return task;
    }

    /**
     * Recupera l'esercizio corrispondente al nome specificato.
     *
     * @param nome il nome dell'esercizio di cui recuperare i dati.
     * @return un oggetto Task che rappresenta l'operazione asincrona di recupero dell'esercizio.
     */
    public Task<DocumentSnapshot> doRetrieveByName(String nome) {
        Task<DocumentSnapshot> task = dbHelper.collection("esercizi").document(nome).get();

        return task;
    }

    public Task<QuerySnapshot> doRetrieveAll() {
        Task<QuerySnapshot> task = dbHelper.collection("esercizi").get();

        return task;
    }

    /**
     * Recupera tutti gli esercizi presenti nella collezione "esercizi" della base di dati Firestore.
     *
     * @return un oggetto Task che rappresenta l'operazione asincrona di recupero di tutti gli esercizi.
     */
    public String doSaveDettaglioEsercizio(Map<String, Object> dettagli) {
        DocumentReference docRef = dbHelper.collection("dettagli_esercizi").document();

        String esercizioPath = (String) dettagli.get("esercizio");

        DocumentReference docRefEsercizio = dbHelper.document(esercizioPath);

        dettagli.put("esercizio", docRefEsercizio);

        String id = docRef.getPath();

        docRef.set(dettagli);

        return id;
    }

}
