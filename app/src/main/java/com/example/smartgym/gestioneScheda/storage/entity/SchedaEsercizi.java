package com.example.smartgym.gestioneScheda.storage.entity;

import com.example.smartgym.infoUtenti.storage.entity.Atleta;

import java.io.Serializable;
import java.util.ArrayList;

public class SchedaEsercizi implements Serializable {

    private String id;

    private String nome;

    private ArrayList<Esercizio> esercizi;

    private Boolean inUso;

    private Boolean pubblica;

    private String modalita;

    private Atleta autore;

    public SchedaEsercizi() {
    }

    public SchedaEsercizi(String nome) {
        this.nome = nome;
    }

    public SchedaEsercizi(String id, String nome, ArrayList<Esercizio> esercizi, boolean inUso, boolean pubblica, String modalita, Atleta autore) {
        this.id = id;
        this.nome = nome;
        this.esercizi = esercizi;
        this.inUso = inUso;
        this.pubblica = pubblica;
        this.modalita = modalita;
        this.autore = autore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Esercizio> getEsercizi() {
        return esercizi;
    }

    public void setEsercizi(ArrayList<Esercizio> esercizi) {
        this.esercizi = esercizi;
    }

    public boolean isInUso() {
        return inUso;
    }

    public void setInUso(boolean inUso) {
        this.inUso = inUso;
    }

    public boolean isPubblica() {
        return pubblica;
    }

    public void setPubblica(boolean pubblica) {
        this.pubblica = pubblica;
    }

    public String getModalita() {
        return modalita;
    }

    public void setModalita(String modalita) {
        this.modalita = modalita;
    }

    public Atleta getAutore() {
        return autore;
    }

    public void setAutore(Atleta autore) {
        this.autore = autore;
    }
}
