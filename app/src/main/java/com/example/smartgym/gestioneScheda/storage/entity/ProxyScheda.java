package com.example.smartgym.gestioneScheda.storage.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Questa classe rappresenta un proxy per la scheda di esercizi, implementando l'interfaccia SchedaEsercizi e la serializzazione.
 * Il proxy viene utilizzato per ridurre il traffico di rete, inviando solo l'ID e il nome della scheda, e caricando l'elenco degli esercizi solo quando necessario.
 */
public class ProxyScheda implements Serializable, SchedaEsercizi {

    private String id;
    private String nome;
    private ArrayList<Esercizio> esercizi;

    /**
     * Costruttore per il proxy della scheda di esercizi.
     *
     * @param id   l'ID della scheda di esercizi
     * @param nome il nome della scheda di esercizi
     */
    public ProxyScheda(String id, String nome) {
        this.id = id;
        this.nome = nome;
        esercizi = new ArrayList<>();
    }

    /**
     * Restituisce l'ID della scheda di esercizi.
     *
     * @return l'ID della scheda di esercizi
     */
    public String getId() {
        return id;
    }

    /**
     * Imposta l'ID della scheda di esercizi.
     *
     * @param id l'ID della scheda di esercizi
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Imposta il nome della scheda di esercizi.
     *
     * @param nome il nome della scheda di esercizi
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Imposta l'elenco degli esercizi per la scheda di esercizi.
     *
     * @param esercizi l'elenco degli esercizi per la scheda di esercizi
     */
    public void setEsercizi(ArrayList<Esercizio> esercizi) {
        this.esercizi = esercizi;
    }

    /**
     * Restituisce l'elenco degli esercizi per la scheda di esercizi.
     *
     * @return l'elenco degli esercizi per la scheda di esercizi
     */
    @Override
    public ArrayList<Esercizio> getEsercizi() {
        return this.esercizi;
    }

    /**
     * Restituisce il nome della scheda di esercizi.
     *
     * @return il nome della scheda di esercizi
     */
    @Override
    public String getNome() {
        return this.nome;
    }
}
