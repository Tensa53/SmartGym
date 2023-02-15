package com.example.smartgym.gestioneScheda.storage.entity;

import com.example.smartgym.infoUtenti.storage.entity.Atleta;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe RealScheda rappresenta l'implementazione concreta dell'interfaccia SchedaEsercizi e descrive una scheda di esercizi.
 * <p>
 * Una scheda di esercizi è composta da un id, un nome, una lista di esercizi, una flag che indica se la scheda è in uso, una flag che indica se la scheda è pubblica,
 * <p>
 * la modalità di esecuzione della scheda e l'atleta che ha creato la scheda.
 */
public class RealScheda implements Serializable, SchedaEsercizi {

    private String id;

    private String nome;

    private ArrayList<Esercizio> esercizi;

    private Boolean inUso;

    private Boolean pubblica;

    private String modalita;

    private Atleta autore;

    /**
     * Costruttore senza argomenti che inizializza la lista di esercizi.
     */
    public RealScheda() {
        this.esercizi = new ArrayList<>();
    }

    /**
     * Costruttore che prende come argomento il nome della scheda.
     *
     * @param nome il nome della scheda di esercizi.
     */
    public RealScheda(String nome) {
        this.nome = nome;
    }

    /**
     * Costruttore che prende come argomenti id, nome, esercizi, flag inUso, flag pubblica, modalità e autore della scheda.
     *
     * @param id       l'id della scheda di esercizi.
     * @param nome     il nome della scheda di esercizi.
     * @param esercizi la lista di esercizi della scheda.
     * @param inUso    flag che indica se la scheda è in uso.
     * @param pubblica flag che indica se la scheda è pubblica.
     * @param modalita la modalità di esecuzione della scheda di esercizi.
     * @param autore   l'atleta che ha creato la scheda di esercizi.
     */
    public RealScheda(String id, String nome, ArrayList<Esercizio> esercizi, boolean inUso, boolean pubblica, String modalita, Atleta autore) {
        this.id = id;
        this.nome = nome;
        this.esercizi = esercizi;
        this.inUso = inUso;
        this.pubblica = pubblica;
        this.modalita = modalita;
        this.autore = autore;
    }

    /**
     * Costruttore che prende come argomenti id, nome e esercizi della scheda di esercizi.
     *
     * @param id       l'id della scheda di esercizi.
     * @param nome     il nome della scheda di esercizi.
     * @param esercizi la lista di esercizi della scheda.
     */
    public RealScheda(String id, String nome, ArrayList<Esercizio> esercizi) {
        this.id = id;
        this.nome = nome;
        this.esercizi = esercizi;
    }

    /**
     * Metodo che restituisce l'id della scheda di esercizi.
     *
     * @return l'id della scheda di esercizi.
     */
    public String getId() {
        return id;
    }

    /**
     * Metodo che imposta l'id della scheda di esercizi.
     *
     * @param id l'id della scheda di esercizi.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Restituisce il nome della scheda di esercizi.
     *
     * @return il nome della scheda di esercizi
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome della scheda di esercizi.
     *
     * @param nome il nuovo nome della scheda di esercizi
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce l'elenco degli esercizi che compongono la scheda di esercizi.
     *
     * @return l'elenco degli esercizi della scheda di esercizi
     */
    public ArrayList<Esercizio> getEsercizi() {
        return esercizi;
    }

    /**
     * Imposta l'elenco degli esercizi che compongono la scheda di esercizi.
     *
     * @param esercizi l'elenco degli esercizi da impostare per la scheda di esercizi
     */
    public void setEsercizi(ArrayList<Esercizio> esercizi) {
        this.esercizi = esercizi;
    }

    /**
     * Aggiunge un nuovo esercizio alla scheda di esercizi.
     *
     * @param esercizio l'esercizio da aggiungere alla scheda di esercizi
     */
    public void aggiungiEsercizio(Esercizio esercizio) {
        esercizi.add(esercizio);
    }

    /**
     * Restituisce il numero di esercizi presenti nella scheda di esercizi.
     *
     * @return il numero di esercizi presenti nella scheda di esercizi
     */
    public int getNumEsercizi() {
        return esercizi.size();
    }

    /**
     * Rimuove un esercizio dalla scheda di esercizi.
     *
     * @param esercizio l'esercizio da rimuovere dalla scheda di esercizi
     */
    public void rimuoviEsercizio(Esercizio esercizio) {
        esercizi.remove(esercizio);
    }

    /**
     * Restituisce true se la scheda di esercizi è in uso, false altrimenti.
     *
     * @return true se la scheda di esercizi è in uso, false altrimenti
     */
    public boolean isInUso() {
        return inUso;
    }

    /**
     * Imposta lo stato di utilizzo della scheda di esercizi.
     *
     * @param inUso il nuovo stato di utilizzo della scheda di esercizi
     */
    public void setInUso(boolean inUso) {
        this.inUso = inUso;
    }

    /**
     * Restituisce true se la scheda di esercizi è pubblica, false altrimenti.
     *
     * @return true se la scheda di esercizi è pubblica, false altrimenti
     */
    public boolean isPubblica() {
        return pubblica;
    }

    /**
     * Imposta lo stato di pubblicazione della scheda di esercizi.
     *
     * @param pubblica il nuovo stato di pubblicazione della scheda di esercizi
     */
    public void setPubblica(boolean pubblica) {
        this.pubblica = pubblica;
    }

    /**
     * Restituisce la modalità della scheda di esercizi.
     *
     * @return la modalità della scheda di esercizi.
     */
    public String getModalita() {
        return modalita;
    }

    /**
     * Imposta la modalità della scheda di esercizi.
     *
     * @param modalita la modalità da impostare.
     */
    public void setModalita(String modalita) {
        this.modalita = modalita;
    }

    /**
     * Restituisce l'atleta autore della scheda di esercizi.
     *
     * @return l'atleta autore della scheda di esercizi.
     */
    public Atleta getAutore() {
        return autore;
    }

    /**
     * Imposta l'atleta autore della scheda di esercizi.
     *
     * @param autore l'atleta autore da impostare.
     */
    public void setAutore(Atleta autore) {
        this.autore = autore;
    }
}
