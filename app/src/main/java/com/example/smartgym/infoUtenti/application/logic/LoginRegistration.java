package com.example.smartgym.infoUtenti.application.logic;

import com.example.smartgym.infoUtenti.storage.dataAccess.AtletaDAO;
import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginRegistration {

    //login,logout,registrazione,cancellazione

    private FirebaseAuth mauth;
    private AtletaDAO atletaDAO;

    public LoginRegistration() {
        mauth = FirebaseAuth.getInstance();
        atletaDAO = new AtletaDAO();
    }

    public Task<AuthResult> login(String email, String password) {
        Task<AuthResult> task = mauth.signInWithEmailAndPassword(email, password);

        return task;
    }

    public FirebaseUser getUserLogged(){
        FirebaseUser user = mauth.getCurrentUser();

        return user;
    }

    public void logout() {
        mauth.signOut();

        return;
    }


    public Task<AuthResult> createUser(String email, String password) {
        Task<AuthResult> task = mauth.createUserWithEmailAndPassword(email, password);

        return task;
    }

    public Task<Void> saveAthlete(Atleta atleta, String id) {
        return atletaDAO.doSaveAthlete(atleta,id);
    }

    public Task<Void> deleteUser(String email) {
        Task<Void> task = mauth.getCurrentUser().delete();

        return task;
    }

}
