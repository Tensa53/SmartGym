package com.example.smartgym.gestioneScheda.storage.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class ProxyScheda implements Serializable,SchedaEsercizi{

    private String id;
    private String nome;
    private ArrayList<Esercizio> esercizi;

    public ProxyScheda(String id,String nome) {
        this.id = id;
        this.nome = nome;
        esercizi = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEsercizi(ArrayList<Esercizio> esercizi) {
        this.esercizi = esercizi;
    }

    @Override
    public ArrayList<Esercizio> getEsercizi() {
        return this.esercizi;
    }

    @Override
    public String getNome() {
        return  this.nome;
    }
}
