package com.example.smartgym.infoUtenti.application.logic;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * La classe LoginRegistration si occupa delle operazioni di login, registrazione e cancellazione
 * del profilo utente
 */
public class LoginRegistration {

    private FirebaseAuth mauth;

    /**
     * Costruttore della classe che inizializza l'istanza del servizio di autenticazione
     */
    public LoginRegistration() {
        mauth = FirebaseAuth.getInstance();
    }

    /**
     * Effettua il login nel sistema
     *
     * @param email, email del profilo
     * @param password, password del profilo
     * @return Task<AuthResult> che rappresenta il risultato dell'operazione
     */
    public Task<AuthResult> login(String email, String password) {
        Task<AuthResult> task = mauth.signInWithEmailAndPassword(email, password);

        return task;
    }

    /**
     * Recupera l'utente attualmente loggato nel sistema
     *
     * @return FireBaseUser, un'istanza dell'utente relativa al servizio di autenticazione
     */
    public FirebaseUser getUserLogged(){
        FirebaseUser user = mauth.getCurrentUser();

        return user;
    }

    /**
     * Effettua il logout dal sistema
     */
    public void logout() {
        mauth.signOut();

        return;
    }

    /**
     * Memorizza il record di autenticazione creando l'utente nel servizio di autenticazione
     *
     * @param email, email del profilo
     * @param password, password del profilo
     * @return Task<AuthResult> che rappresenta il risultato dell'operazione
     */
    public Task<AuthResult> createUser(String email, String password) {
        Task<AuthResult> task = mauth.createUserWithEmailAndPassword(email, password);

        return task;
    }

    /**
     * Cancella il record di autenticazione dell'utente
     *
     * @return Task<Void> rappresenta il risultato dell'operazione
     */
    public Task<Void> deleteUser() {
        Task<Void> task = mauth.getCurrentUser().delete();

        return task;
    }

}
