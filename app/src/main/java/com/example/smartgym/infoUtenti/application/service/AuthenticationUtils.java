package com.example.smartgym.infoUtenti.application.service;

import com.example.smartgym.infoUtenti.storage.dataAccess.UtenteDAO;
import com.example.smartgym.infoUtenti.storage.entity.Utente;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

public class AuthenticationUtils {

    //login,logout,registrazione,cancellazione

    public Task<AuthResult> login(String email, String password) {

        FirebaseAuth mauth = FirebaseAuth.getInstance();

        Task<AuthResult> task = mauth.signInWithEmailAndPassword(email, password);

        return task;
    }

    public static void logout() {
        FirebaseAuth mauth = FirebaseAuth.getInstance();

        mauth.signOut();

        return;
    }


    public Task<AuthResult> createUser(String email, String password) {
        FirebaseAuth mauth = FirebaseAuth.getInstance();

        Task<AuthResult> task = mauth.createUserWithEmailAndPassword(email, password);

        return task;
    }

    public Task<DocumentReference> saveUser(Utente utente) {
        UtenteDAO utenteDAO = new UtenteDAO();

        Task<DocumentReference> task = utenteDAO.doSaveUser(utente);

        return task;
    }

    public void deleteUser(String email) {
        //TODO
    }

}
