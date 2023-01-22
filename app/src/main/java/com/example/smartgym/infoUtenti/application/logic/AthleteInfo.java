package com.example.smartgym.infoUtenti.application.logic;

import com.example.smartgym.infoUtenti.storage.dataAccess.AtletaDAO;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class AthleteInfo {

    AtletaDAO atletaDAO;

    public AthleteInfo() {
        atletaDAO = new AtletaDAO();
    }

    public Task<DocumentSnapshot> getAthletebyEmail(String email){
        Task<DocumentSnapshot> task = atletaDAO.doRetrieveAthleteDocByEmail(email);

        return task;
    }

    public void editAthleteInfo(){
        //TODO
    }

    public void insertAthleteFeatures(){
        //TODO
    }

    public void editAthleteFeatures(){
        //TODO
    }

    public void deleteAthlete(String email){
        atletaDAO.doDeleteAthlete(email);

        return;
    }

}
