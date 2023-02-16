package com.example.smartgym.infoUtenti.application.logic;

import com.example.smartgym.infoUtenti.storage.dataAccess.AtletaDAO;
import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

/**
 * La classe AthleteInfo si occupa delle operazioni relative all'inserimento, modifica e
 * cancellazione delle informazioni e caratteristiche dell'atleta oltre che del loro recupero dal
 * database Firebase
 */
public class AthleteInfo {

    AtletaDAO atletaDAO;

    /**
     * Costruttore della classe che inizializza il DAO di riferimento
     */
    public AthleteInfo() {
        atletaDAO = new AtletaDAO();
    }

    /**
     * Questo metodo permette di salvare l'atleta nel db
     *
     * @param atleta, un'istanza della classe atleta
     * @param id, l'id del documento da creare nel db
     * @return Task<Void> che rappresenta il risultato dell'operazione
     */
    public Task<Void> saveAthlete(Atleta atleta, String id) {
        return atletaDAO.doSaveAthlete(atleta,id);
    }

    /**
     * Recupera un atleta a partire dall'ID
     * @param id, l'id del documento dell'atleta
     * @return Task<DocumentSnapshot> che rappresenta il risultato dell'operazione
     */
    public Task<DocumentSnapshot> getAthletebyId(String id){
        Task<DocumentSnapshot> task = atletaDAO.doRetrieveAthleteDocById(id);
        return task;
    }

    /**
     * Modifica e aggiorna le informazioni anagrafiche personali dell'atleta
     *
     * @param atleta, un'istanza della classe atleta che incapsula le informazioni
     * @param id, l'id del documento da modificare ed aggiornare
     * @return Task<Void> che rappresenta il risultato dell'operazione
     */
    public Task<Void> editAthleteInfo(Atleta atleta, String id){
        return atletaDAO.doUpdateAthlete(atleta, id);
    }

    /**
     * Salva le caratteristiche dell'atleta nel db
     *
     * @param atleta, un'istanza della classe atleta che incapsula le informazioni
     * @param id, l'id del documento
     * @return Task<Void> che rappresenta il risultato dell'operazione
     */
    public Task<Void> insertAthleteFeatures(Atleta atleta, String id){
        return atletaDAO.doUpdateAthlete(atleta, id);
    }

    /**
     * Modifica e aggiorna le caratteristiche dell'atleta
     *
     * @param atleta, un'istanza della classe atleta che incapsula le informazioni
     * @param id, l'id del documento da modificare ed aggiornare
     * @return Task<Void> che rappresenta il risultato dell'operazione
     */
    public Task<Void> editAthleteFeatures(Atleta atleta, String id){
        return atletaDAO.doUpdateAthlete(atleta, id);
    }

    /**
     * Rimuove l'atleta dal db
     * @param id, l'id del documento
     * @return Task<Void> che rappresenta il risultato dell'operazione
     */
    public Task<Void> deleteAthlete(String id){
        return atletaDAO.doDeleteAthlete(id);
    }

}
