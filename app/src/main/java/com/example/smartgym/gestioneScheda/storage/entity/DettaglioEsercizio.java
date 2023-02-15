package com.example.smartgym.gestioneScheda.storage.entity;

import java.io.Serializable;

/**
 * La classe DettaglioEsercizio rappresenta le informazioni aggiuntive per un esercizio, come il numero di ripetizioni e la durata dell'esercizio.
 */
public class DettaglioEsercizio implements Serializable {

    private Integer ripetizioni;
    private Integer durata;

    /**
     * Costruttore vuoto della classe DettaglioEsercizio.
     */
    public DettaglioEsercizio() {
    }

    /**
     * Costruttore che inizializza i valori del numero di ripetizioni e della durata dell'esercizio.
     *
     * @param ripetizioni Il numero di ripetizioni dell'esercizio fisico.
     * @param durata      La durata dell'esercizio fisico.
     */
    public DettaglioEsercizio(Integer ripetizioni, Integer durata) {
        this.ripetizioni = ripetizioni;
        this.durata = durata;
    }

    /**
     * Restituisce il numero di ripetizioni dell'esercizio.
     *
     * @return Il numero di ripetizioni dell'esercizio.
     */
    public Integer getRipetizioni() {
        return ripetizioni;
    }

    /**
     * Imposta il numero di ripetizioni dell'esercizio.
     *
     * @param ripetizioni Il numero di ripetizioni dell'esercizio.
     */
    public void setRipetizioni(Integer ripetizioni) {
        this.ripetizioni = ripetizioni;
    }

    /**
     * Restituisce la durata dell'esercizio fisico.
     *
     * @return La durata dell'esercizio fisico.
     */
    public Integer getDurata() {
        return durata;
    }

    /**
     * Imposta la durata dell'esercizio fisico.
     *
     * @param durata La durata dell'esercizio fisico.
     */
    public void setDurata(Integer durata) {
        this.durata = durata;
    }
}
