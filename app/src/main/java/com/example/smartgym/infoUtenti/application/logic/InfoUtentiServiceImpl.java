package com.example.smartgym.infoUtenti.application.logic;

import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

/**
 * Implementazione dell'interfaccia InfoUtentiService che fornisce i metodi per login registrazione
 * cancellazione e recupero dei dati dell'utente
 */
public class InfoUtentiServiceImpl implements InfoUtentiService {

    LoginRegistration loginRegistration;
    AthleteInfo athleteInfo;

    /**
     * Costruttore della classe che inizializza un'istanza della classe LoginRegistration che si
     * occupa dei servizi di autenticazione e registrazione dell'utente. Inoltre inzializza un'istanza
     * della classe AthleteInfo che si occupa di inserimento/modifica, recupero e cancellazione
     * delle informazioni e caratteristiche dell'utente atleta
     */
    public InfoUtentiServiceImpl(){
        loginRegistration = new LoginRegistration();
        athleteInfo = new AthleteInfo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task<AuthResult> login(String email, String password) {
        return loginRegistration.login(email,password);
    }

    /**
     * {@inheritDoc}
     */
    public FirebaseUser getUserLogged(){
        return loginRegistration.getUserLogged();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void logout() {
        loginRegistration.logout();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task<AuthResult> createUser(String email, String password) {
        return loginRegistration.createUser(email,password);
    }

    /**
     * {@inheritDoc}
     */
    public Task<Void> saveAthlete(Atleta atleta, String id){
        return athleteInfo.saveAthlete(atleta,id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task<DocumentSnapshot> getAthletebyId(String id) {
        return athleteInfo.getAthletebyId(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task<Void> editAthleteInfo(Atleta atleta, String id) {
        return athleteInfo.editAthleteInfo(atleta, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task<Void> insertAthleteFeatures(Atleta atleta, String id) {
        return  athleteInfo.insertAthleteFeatures(atleta, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task<Void> editAthleteFeatures(Atleta atleta, String id) {
        return athleteInfo.editAthleteFeatures(atleta, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task<Void> deleteUser() {
        return loginRegistration.deleteUser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task<Void> deleteAthlete(String id) {
        return athleteInfo.deleteAthlete(id);
    }

}
