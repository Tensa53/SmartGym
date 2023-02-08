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

    public Task<Void> saveAthlete(Atleta atleta, String id) {
        return atletaDAO.doSaveAthlete(atleta,id);
    }

    public Task<DocumentSnapshot> getAthletebyId(String id){
        Task<DocumentSnapshot> task = atletaDAO.doRetrieveAthleteDocById(id);
        return task;
    }

    public Task<Void> editAthleteInfo(Atleta atleta, String id){
        return atletaDAO.doUpdateAthlete(atleta, id);
    }

    public Task<Void> insertAthleteFeatures(Atleta atleta, String id){
        return atletaDAO.doUpdateAthlete(atleta, id);
    }

    public Task<Void> editAthleteFeatures(Atleta atleta, String id){
        return atletaDAO.doUpdateAthlete(atleta, id);
    }

    public Task<Void> deleteAthlete(String id){
        return atletaDAO.doDeleteAthlete(id);
    }

}
