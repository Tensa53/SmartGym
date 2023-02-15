package com.example.smartgym.gestioneScheda.application.logic;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

/**
 * Questa interfaccia definisce i metodi per la gestione delle schede di allenamento nel database.
 * Le implementazioni di questa interfaccia forniscono i servizi per salvare, aggiornare, ottenere e rimuovere le schede dal database.
 */
public interface GestioneSchedaService {

    /**
     * Salva una scheda di allenamento nel database.
     *
     * @param scheda una mappa di dati rappresentante la scheda da salvare
     * @return un oggetto Task<Void> che rappresenta l'esito dell'operazione di salvataggio
     */
    Task<Void> saveScheda(Map<String, Object> scheda);

    /**
     * Aggiorna una scheda di allenamento esistente nel database.
     *
     * @param scheda   una mappa di dati rappresentante la scheda aggiornata
     * @param schedaId l'identificatore univoco della scheda da aggiornare
     * @return un oggetto Task<Void> che rappresenta l'esito dell'operazione di aggiornamento
     */
    Task<Void> updateScheda(Map<String, Object> scheda, String schedaId);

    /**
     * Ottiene una scheda di allenamento dal database tramite il suo identificatore univoco.
     *
     * @param schedaId l'identificatore univoco della scheda da ottenere
     * @return un oggetto Task<DocumentSnapshot> che rappresenta la scheda richiesta, se presente nel database
     */
    Task<DocumentSnapshot> getSchedaById(String schedaId);

    /**
     * Ottiene tutte le schede di allenamento di un determinato utente dal database.
     *
     * @param userId l'identificatore univoco dell'utente di cui si vogliono ottenere le schede
     * @return un oggetto Task<QuerySnapshot> che rappresenta l'elenco delle schede richieste, se presenti nel database
     */
    Task<QuerySnapshot> getAllUserSchede(String userId);

    /**
     * Ottiene l'elenco di tutti gli esercizi di allenamento presenti nel database.
     *
     * @return un oggetto Task<QuerySnapshot> che rappresenta l'elenco degli esercizi richiesti, se presenti nel database
     */
    Task<QuerySnapshot> getAllEsercizi();

    /**
     * Ottiene la scheda di allenamento attualmente in uso da un determinato utente dal database.
     *
     * @param userId l'identificatore univoco dell'utente di cui si vuole ottenere la scheda in uso
     * @return un oggetto Task<QuerySnapshot> che rappresenta la scheda in uso dall'utente richiesto, se presente nel database
     */
    Task<QuerySnapshot> getSchedaInUso(String userId);
}
