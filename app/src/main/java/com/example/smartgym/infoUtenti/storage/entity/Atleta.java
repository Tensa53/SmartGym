package com.example.smartgym.infoUtenti.storage.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Atleta implements Serializable {

    private String nome;
    private String cognome;
    private String email;
    private String sesso;
    private Date dataDiNascita;
    private int allenamentiSettimanali;
    private int altezza;
    private int peso;
    private String esperienza;

    public Atleta() {

    }

    public Atleta(String nome, String cognome, String email, String sesso, Date dataDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.sesso = sesso;
        this.dataDiNascita = dataDiNascita;
        this.allenamentiSettimanali = 0;
        this.altezza = 0;
        this.peso = 0;
        this.esperienza = "principiante";
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

    public String getEsperienza() {
        return esperienza;
    }

    public Integer esperienzaValue() {
        if (esperienza.equalsIgnoreCase("Principiante"))
            return 0;

        if (esperienza.equalsIgnoreCase("Intermedio"))
            return 1;

        if (esperienza.equalsIgnoreCase("Esperto"))
            return 2;

        return 0;
    }

    public void setEsperienza(String esperienza) {
        this.esperienza = esperienza;
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

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public String formattedDataDiNascita() {
        SimpleDateFormat dateFormat = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        }

        return dateFormat.format(getDataDiNascita());
    }

    public void setDataDiNascita(Date dataDiNascita) {
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
                ", traguardo='" + '\'' +
                '}';
    }

    public String toFormattedString() {

        return "Nome: " + getNome() + "\n" + "Cognome: " + getCognome() + "\n" + "Email: " + getEmail() + "\n" + "Sesso: " + sesso + "\n" + "dataDinascita: " + formattedDataDiNascita()
                + "\n" + "Allenamenti settimanali: " + allenamentiSettimanali + "\n" + "Altezza: " + altezza + "\n" + "Peso: " + peso;
    }
}
