package com.example.smartgym.infoUtenti.application.activity;

import com.example.smartgym.infoUtenti.storage.entity.Atleta;

public interface ActivityReceiver {

    String getIdSchedaInUso();

    void setIdSchedaInUso(String id);

    Atleta getAtleta();

    void setAtleta(Atleta atleta);

}
