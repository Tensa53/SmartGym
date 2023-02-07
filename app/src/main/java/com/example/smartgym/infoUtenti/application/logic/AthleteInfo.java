package com.example.smartgym.infoUtenti.application.logic;

import com.example.smartgym.infoUtenti.storage.dataAccess.AtletaDAO;
import com.example.smartgym.infoUtenti.storage.entity.Atleta;
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

    public Task<Void> editAthleteInfo(Atleta atleta, String id){
        return atletaDAO.doUpdateAthlete(atleta, id);
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
