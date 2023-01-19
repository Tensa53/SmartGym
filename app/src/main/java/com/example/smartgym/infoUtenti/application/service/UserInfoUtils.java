package com.example.smartgym.infoUtenti.application.service;

import com.example.smartgym.infoUtenti.storage.dataAccess.UtenteDAO;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class UserInfoUtils {


    public static Task<DocumentSnapshot> findUserById(String id){
        UtenteDAO utenteDAO = new UtenteDAO();

        Task<DocumentSnapshot> task = utenteDAO.doRetrieveUserDocById(id);

        return task;
    }

    public static Task<QuerySnapshot> findUserbyEmail(String email){
        UtenteDAO utenteDAO = new UtenteDAO();

        Task<QuerySnapshot> task = utenteDAO.doRetrieveUserDocByEmail(email);

        return task;
    }

    public void editUserInfo(){
        //TODO
    }

    public void insertAthleteFeatures(){

    }

    public void editAthleteFeatures(){

    }

    //modifica profilo,inserimento caratteristiche atleta,modifica caratteristiche atleta,getters per Utente dal db

}
