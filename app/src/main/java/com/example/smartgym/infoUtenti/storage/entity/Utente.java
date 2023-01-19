package com.example.smartgym.infoUtenti.storage.entity;

import androidx.annotation.NonNull;

import com.google.firebase.Timestamp;

import java.util.GregorianCalendar;

public class Utente {

    private String nome;
    private String cognome;
    private String mail;
    private boolean sesso;
    private Timestamp dataDiNascita;

    public Utente(){

    }

    public Utente(String nome, String cognome, String mail, boolean sesso, Timestamp dataDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.sesso = sesso;
        this.dataDiNascita = dataDiNascita;
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

    public boolean isSesso() {
        return sesso;
    }

    public void setSesso(boolean sesso) {
        this.sesso = sesso;
    }

    public Timestamp getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Timestamp dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }
}
