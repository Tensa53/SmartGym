package com.example.smartgym.infoUtenti.storage.entity;

import android.net.Uri;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Atleta implements Serializable {

    private String nome;
    private String cognome;
    private String email;
    private boolean sesso;
    private Timestamp dataDiNascita;
    private int allenamentiSettimanali;
    private int altezza;
    private int peso;
    private String traguardo;

    public Atleta() {

    }

    public Atleta(String nome, String cognome, String email, boolean sesso, Timestamp dataDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.sesso = sesso;
        this.dataDiNascita = dataDiNascita;
        this.allenamentiSettimanali = 0;
        this.altezza = 0;
        this.peso = 0;
        this.traguardo = "";
    }

    public boolean areFeaturesEmpty(){
        boolean res = this.altezza == 0 && this.peso == 0 && this.allenamentiSettimanali == 0;

        return res;
    }

    public int getAllenamentiSettimanali() {
        return allenamentiSettimanali;
    }

    public void setAllenamentiSettimanali(int allenamentiSettimanali) {
        this.allenamentiSettimanali = allenamentiSettimanali;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getFormattedDataDiNascita() {
        Date currentDate = new Date(getDataDiNascita().getSeconds() * 1000);
        SimpleDateFormat dateFormat = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        }

        return dateFormat.format(currentDate);
    }

    public void setDataDiNascita(Timestamp dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public int getAltezza() {
        return altezza;
    }

    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getTraguardo() {
        return traguardo;
    }

    public void setTraguardo(String traguardo) {
        this.traguardo = traguardo;
    }

    @Override
    public String toString() {
        return "Atleta{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", sesso=" + sesso +
                ", dataDiNascita=" + dataDiNascita +
                ", allenamentiSettimanali=" + allenamentiSettimanali +
                ", altezza=" + altezza +
                ", peso=" + peso +
                ", traguardo='" + traguardo + '\'' +
                '}';
    }

    public String toFormattedString() {
        String s = "";

        if (sesso)
            s = "Maschio";

        return "Nome: " + getNome() + "\n" + "Cognome: " + getCognome() + "\n" + "Email: " + getEmail() + "\n" + "Sesso: " + s + "\n" + "dataDinascita: " + getFormattedDataDiNascita()
                + "\n" + "Allenamenti settimanali: " + allenamentiSettimanali + "\n" + "Altezza: " + altezza + "\n" + "Peso: " + peso + "\n" + "Traguardo:" + traguardo;
    }
}
