package com.example.smartgym.infoUtenti.application.logic;

import com.example.smartgym.infoUtenti.storage.dataAccess.AtletaDAO;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

public class AthleteInfo {

    AtletaDAO atletaDAO;

    public AthleteInfo() {
        atletaDAO = new AtletaDAO();
    }

    public Task<DocumentSnapshot> getAthletebyId(String id){
        Task<DocumentSnapshot> task = atletaDAO.doRetrieveAthleteDocById(id);
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

    public void deleteAthlete(String id){
        atletaDAO.doDeleteAthlete(id);

        return;
    }

}
