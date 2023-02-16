package com.example.smartgym.gestioneScheda.application.logic;

import com.example.smartgym.gestioneScheda.storage.dataAcess.EsercizioDAO;
import com.example.smartgym.gestioneScheda.storage.dataAcess.SchedaEserciziDAO;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

/**
 * La classe SchedaLogic gestisce tutte le operazioni sulla collezione di schede e dettagli esercizio nel database Firebase.
 * In particolare, permette di salvare una nuova scheda o di aggiornare una scheda esistente, di recuperare una scheda a partire dall'ID, di ottenere
 * tutte le schede di un determinato utente o quelle contenenti esercizi per una determinata parte del corpo, di impostare una scheda come in uso da parte dell'utente,
 * di salvare i dettagli di un esercizio, e di recuperare l'elenco completo degli esercizi.
 * Per eseguire tali operazioni, la classe fa uso delle classi SchedaEserciziDAO ed EsercizioDAO, che gestiscono rispettivamente la collezione di schede e la collezione
 * di dettagli esercizio.
 */
public class SchedaLogic {

    private SchedaEserciziDAO schedaEserciziDAO;
    private EsercizioDAO esercizioDAO;

    /**
     * Costruttore della classe. Inizializza le variabili schedaEserciziDAO ed esercizioDAO
     * come rispettivamente istanze di SchedaEserciziDAO ed EsercizioDAO.
     */
    public SchedaLogic() {
        schedaEserciziDAO = new SchedaEserciziDAO();
        esercizioDAO = new EsercizioDAO();
    }

    /**
     * Salva una nuova scheda nel database.
     *
     * @param scheda Mappa contenente i dati della scheda da salvare.
     * @return Task<Void> che rappresenta il risultato dell'operazione.
     */
    public Task<Void> saveScheda(Map<String, Object> scheda) {
        return schedaEserciziDAO.doSaveScheda(scheda);
    }

    /**
     * Aggiorna una scheda esistente.
     *
     * @param scheda   Mappa contenente i nuovi dati da aggiornare.
     * @param schedaId ID della scheda da aggiornare.
     * @return Task<Void> che rappresenta il risultato dell'operazione.
     */
    public Task<Void> updateScheda(Map<String, Object> scheda, String schedaId) {
        return schedaEserciziDAO.doUpdateSchedaById(scheda, schedaId);
    }

    /**
     * Recupera una scheda a partire dall'ID.
     *
     * @param schedaId ID della scheda da recuperare.
     * @return Task<DocumentSnapshot> che rappresenta il risultato dell'operazione.
     */
    public Task<DocumentSnapshot> getSchedaById(String schedaId) {
        return schedaEserciziDAO.doRetrieveSchedaByDocumentId(schedaId);
    }

    /**
     * Recupera tutte le schede di un determinato utente.
     *
     * @param userId ID dell'utente di cui si vogliono recuperare le schede.
     * @return Task<QuerySnapshot> che rappresenta il risultato dell'operazione.
     */
    public Task<QuerySnapshot> getSchedaInUso(String userId) {
        return schedaEserciziDAO.doRetrieveSchedaInUso(userId);
    }

    /**
     * Restituisce tutte le schede di allenamento dell'utente specificato.
     *
     * @param userId l'ID dell'utente di cui si vogliono ottenere le schede
     * @return una Promise che restituisce una QuerySnapshot contenente tutte le schede di allenamento dell'utente specificato
     */
    public Task<QuerySnapshot> getAllUserSchede(String userId) {
        return schedaEserciziDAO.doRetrieveAllSchedeByUserId(userId);
    }

    /**
     * Imposta se una scheda di allenamento è in uso dall'utente specificato.
     *
     * @param idNuovaSchedaInuso l'ID della scheda di allenamento che si vuole impostare in uso
     * @param inUso              lo stato di utilizzo della scheda di allenamento ("in uso" o "non in uso")
     * @param b                  valore booleano che indica se la scheda è in uso o meno dall'utente
     * @return una Promise che indica se l'operazione è stata completata con successo
     */
    public Task<Void> setSchedaInUso(String idNuovaSchedaInuso, String inUso, boolean b) {
        return schedaEserciziDAO.doUpdateSchedaInUso(idNuovaSchedaInuso, inUso, b);
    }

    /**
     * Salva i dettagli di un esercizio nel database.
     *
     * @param dettagli una mappa contenente i dettagli dell'esercizio da salvare
     * @return l'ID dell'esercizio salvato
     */
    public String saveDettaglioEsercizio(Map<String, Object> dettagli) {
        return esercizioDAO.doSaveDettaglioEsercizio(dettagli);
    }

    /**
     * Restituisce tutti gli esercizi presenti nel database.
     *
     * @return una Promise che restituisce una QuerySnapshot contenente tutti gli esercizi presenti nel database
     */
    public Task<QuerySnapshot> getAllEsercizi() {
        return esercizioDAO.doRetrieveAllEsercizi();
    }

    /**
     * Restituisce tutti gli esercizi presenti nel database che riguardano una specifica parte del corpo.
     *
     * @param parteDelCorpo la parte del corpo di cui si vogliono ottenere gli esercizi
     * @return una Promise che restituisce una QuerySnapshot contenente tutti gli esercizi presenti nel database che riguardano la parte del corpo specificata
     */
    public Task<QuerySnapshot> getAllEserciziParteDelCorpo(String parteDelCorpo) {
        return esercizioDAO.doRetrieveEsercizioByParteDelCorpo(parteDelCorpo);
    }
}
