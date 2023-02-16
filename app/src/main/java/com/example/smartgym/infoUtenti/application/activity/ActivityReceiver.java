package com.example.smartgym.infoUtenti.application.activity;

import com.example.smartgym.infoUtenti.storage.entity.Atleta;

/**
 * Questa interfaccia definisce i metodi per effettuare il passaggio di dati tra activity
 * e fragment quando avviene l'operazione di attach di quest'ultimo
 */
public interface ActivityReceiver {

    /**
     * Metodo chiamato quando bisogna recuperare l'id della scheda in uso
     *
     * @return String, l'id della scheda in uso fissata nella home dell'app
     */
    String getIdSchedaInUso();

    /**
     * Metodo chiamato quando bisogna settare l'id della nuova scheda in uso
     *
     * @param id l'id dela nuova scheda in uso da fissare nella home dell'app
     */
    void setIdSchedaInUso(String id);

    /**
     * Metodo chiamato quando bisogna recupare l'istanza dell'atleta che utilizza
     * il sistema ed ha effettuato il login
     *
     * @return Atleta, un'istanza della classe contente tutte l'informazioni dell'attuale
     * utente Atleta utilizzatore del sistema
     */
    Atleta getAtleta();

    /**
     * Metodo chiamato per settare l'atleta
     *
     * @param atleta, un'istanza della classe Atleta
     */
    void setAtleta(Atleta atleta);

}
