package com.example.smartgym.storage;

import androidx.annotation.NonNull;

public class Utente {

    private String nome;
    private String cognome;
    private String mail;

    public Utente(){

    }

    public Utente(String nome, String cognome, String mail) {
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
