package com.example.smartgym.infoUtenti.application.logic;
import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

import org.checkerframework.checker.units.qual.A;

/**
 * La seguente classe fornisce i servizi e le funzionalita per il package (sottosistema) InfoUtenti
 */
public class InfoUtentiServiceImpl implements InfoUtentiService {

    LoginRegistration loginRegistration;
    AthleteInfo athleteInfoUtils;

    public InfoUtentiServiceImpl(){
        loginRegistration = new LoginRegistration();
        athleteInfoUtils = new AthleteInfo();
    }

    /**
     * effettua il login dell'utente nel sistema
     *
     * @param email l'indirizzo email associato all'utente
     * @param password la password corrispondente
     * @return il riferimento al task di login per verificarne il successo o il fallimento
     */
    @Override
    public Task<AuthResult> login(String email, String password) {
        return loginRegistration.login(email,password);
    }

    /*cancellazione,
    inserimento caratteristiche,
    modifica caratteristiche,
    modifica dati profilo*/

    /**
     * verifica se l'utente ha effettuato il login
     *
     * @pre login(String email, String password)
     * @return l'utente attualmente loggato nel sistema
     */
    public FirebaseUser getUserLogged(){
        return loginRegistration.getUserLogged();
    }

    /**
     * effettua il logout dell'utente dal sistema
     */
    @Override
    public void logout() {
        loginRegistration.logout();
    }

    /**
     * crea il record dell'utente per il servizio di autenticazione
     *
     * @param email l'indirizzo email associato all'utente
     * @param password la password corrispondente
     * @return il riferimento all'operazione di creazione autenticazione per verificarne il successo o il fallimento
     */
    @Override
    public Task<AuthResult> createUser(String email, String password) {
        return loginRegistration.createUser(email,password);
    }

    /**
     * salva i dati dell'utente atleta nel database
     *
     * @param atleta istanza della classe Atleta, contiene i dati relativi all'utente atleta
     */
    public void saveAthlete(Atleta atleta, String id){
        loginRegistration.saveAthlete(atleta,id);

        return;
    }

    /**
     *  accede al DAO per permettere di recuperare i dati dell'utente
     *
     * @param email l'indirizzo email associato all'utente
     * @return il riferimento al documento per prelevare i dati
     */
    @Override
    public Task<DocumentSnapshot> getAthletebyId(String id) {
        return athleteInfoUtils.getAthletebyId(id);
    }

    @Override
    public void editAthleteInfo() {
        /*
        athleteInfoUtils.editAthleteInfo();
        return;
         */
    }



    @Override
    public void insertAthleteFeatures() {
        athleteInfoUtils.insertAthleteFeatures();

        return;
    }

    @Override
    public void editAthleteFeatures() {
        athleteInfoUtils.editAthleteFeatures();

        return;
    }

    @Override
    public void deleteUser(String email) {

    }

    @Override
    public Task<Void> deleteAthlete(String email) {
        return loginRegistration.deleteUser(email);
    }

}
