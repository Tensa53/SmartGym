package com.example.smartgym.gestioneScheda.storage.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Questa interfaccia definisce i metodi necessari per accedere alle informazioni di una scheda di allenamento
 * contenente una lista di esercizi.
 */
public interface SchedaEsercizi {

    /**
     * Restituisce un elenco di esercizi presenti nella scheda di allenamento.
     *
     * @return un ArrayList di oggetti Esercizio contenente gli esercizi della scheda di allenamento.
     */
    public ArrayList<Esercizio> getEsercizi();

    /**
     * Restituisce l'identificatore univoco della scheda di allenamento.
     *
     * @return una stringa rappresentante l'ID della scheda di allenamento.
     */
    public String getId();

    /**
     * Restituisce il nome della scheda di allenamento.
     *
     * @return una stringa rappresentante il nome della scheda di allenamento.
     */
    public String getNome();

}
