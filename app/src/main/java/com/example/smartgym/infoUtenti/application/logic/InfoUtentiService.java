package com.example.smartgym.infoUtenti.application.logic;

import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

/**
 * Questa interfaccia definisce i metodi per la gestione delle schede di allenamento nel database.
 * Le implementazioni di questa interfaccia forniscono i servizi per login, registrazione, salvataggio
 * e recupero dei dati dell'utente dal db
 */
public interface InfoUtentiService {

    /**
     * Effettua il login nel sistema
     *
     * @param email, l'email del profilo
     * @param password, la password del profilo
     * @return Task<AuthResult> che rappresenta il risultato dell'operazione
     */
    Task<AuthResult> login(String email, String password);

    /**
     * Restituisce l'utente loggato nel sistema
     *
     * @return FirebaseUser, l'istanza dell'utente rispetto al servizio di autenticazione
     */
    FirebaseUser getUserLogged();

    /**
     * Effettua il logout dal sistema
     */
    void logout ();

    /**
     * Crea l'utente come record nel servizio di autenticazione
     *
     * @param email, l'email dell'utente
     * @param password, la password dell'utente
     * @return Task<AuthResult> rappresenta il risultato dell'operazione
     */
    Task<AuthResult> createUser(String email, String password);

    /**
     * Salva l'atleta nel db
     *
     * @param atleta, l'atleta da salvare
     * @param id, l'id del documento da creare
     * @return Task<Void> rappresenta il risultato dell'operazione
     */
    Task<Void> saveAthlete(Atleta atleta, String id);

    /**
     * Recupera l'atleta dal db attraverso il suo id
     *
     * @param id, l'id del documento dell'atleta
     * @return Task<DocumentSnapshot> rappresenta il risultato dell'operazione
     */
    Task<DocumentSnapshot> getAthletebyId(String id);

    /**
     * Modifica le informazioni dell'atleta
     *
     * @param atleta, l'atleta da aggiornare
     * @param id, l'id del documento da aggiornare
     * @return Task<Vodi> rappresenta il risultato dell'operazione
     */
    Task<Void> editAthleteInfo(Atleta atleta, String id);

    /**
     * Salva le caratteristiche dell'atleta nel db
     *
     * @param atleta, l'atleta da salvare
     * @param id, l'id del documento
     * @return Task<Void> rappresenta il risultato dell'operazione
     */
    Task<Void> insertAthleteFeatures(Atleta atleta, String id);

    /**
     * Modifica le caratteristiche dell'atleta nel db
     *
     * @param atleta, l'atleta da aggiornare
     * @param id, l'id del documento
     * @return Task<Void> rappresenta il risultato dell'operazione
     */
    Task<Void> editAthleteFeatures(Atleta atleta, String id);

    /**
     * Cancella l'utente dal servizioe di autenticazione
     *
     * @return Task<Void> rappresenta il risultato dell'operazione
     */
    Task<Void> deleteUser();

    /**
     * Cancella l'atleta dal db
     *
     * @param id
     * @return Task<Void> rappresenta il risultato dell'operazione
     */
    Task<Void> deleteAthlete(String id);

}
