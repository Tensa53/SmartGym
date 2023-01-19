package com.example.smartgym.infoUtenti.application.service;
import com.example.smartgym.infoUtenti.application.service.InfoUtentiService;
import com.example.smartgym.infoUtenti.storage.dataAccess.UtenteDAO;
import com.example.smartgym.infoUtenti.storage.entity.Utente;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

/**
 * La seguente classe fornisce i servizi e le funzionalita per il package (sottosistema) InfoUtenti
 */
public class InfoUtentiServiceImpl implements InfoUtentiService {

    AuthenticationUtils authenticationUtils;
    UserInfoUtils userInfoUtils;

    public InfoUtentiServiceImpl(){
        authenticationUtils = new AuthenticationUtils();
        userInfoUtils = new UserInfoUtils();
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
        return authenticationUtils.login(email,password);
    }

    /*cancellazione,
    inserimento caratteristiche,
    modifica caratteristiche,
    modifica dati profilo*/


    /**
     * effettua il logout dell'utente dal sistema
     */
    @Override
    public void logout() {
        AuthenticationUtils.logout();
    }

    /**
     * accede al DAO per permettere di recuperare i dati dell'utente
     *
     * @param id l'identificativo del documento dell'utente salvato nel db
     * @return il riferimento all'operazione di query per verificarne il successo e recuperare i dati dell'utente
     */
    @Override
    public Task<DocumentSnapshot> getUserbyId(String id) {
        return userInfoUtils.findUserById(id);
    }

    /**
     *  accede al DAO per permettere di recuperare i dati dell'utente
     *
     * @param email l'indirizzo email associato all'utente
     * @return il riferimento all'operazione di query per verificarne il successo e recuperare i dati dell'utente
     */
    @Override
    public Task<QuerySnapshot> getUserbyEmail(String email) {
        return userInfoUtils.findUserbyEmail(email);
    }

    /**
     * crea il record dell'utente per il servizio di autenticazione
     *
     * @param email l'indirizzo email associato all'utente
     * @param password la password corrispondente
     * @return il riferimento all'operazione di creazione autenticazione per verificarne il successo o il fallimento
     */
    @Override
    public Task<AuthResult> registerUserAuth(String email, String password) {
        return authenticationUtils.createUser(email,password);
    }

    /**
     * salva i dati dell'utente nel database
     *
     * @param utente istanza della classe Utente, contiene i dati relativi all'utente
     * @return il riferimento all'operazione di creazione autenticazione per verificarne il successo o il fallimento
     */
    @Override
    public Task<DocumentReference> registerUserData(Utente utente) {
        return authenticationUtils.saveUser(utente);
    }

    @Override
    public void modificaInfoProfilo() {
        userInfoUtils.editUserInfo();
    }

    @Override
    public void inserimentoCaratteristicheAtleta() {
        userInfoUtils.insertAthleteFeatures();
    }

    @Override
    public void modificaCaratteristicheAtleta() {
        userInfoUtils.editAthleteFeatures();
    }

    @Override
    public void cancellazioneProfilo(String email) {
        authenticationUtils.deleteUser(email);
    }

}
