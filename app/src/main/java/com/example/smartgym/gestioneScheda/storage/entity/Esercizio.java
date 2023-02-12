package com.example.smartgym.gestioneScheda.storage.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Esercizio implements Serializable {

    private String nome;
    private String descrizione;
    private String parteDelCorpo;
    private String tipologia;
    private String difficolta;
    private String esecuzione;
    private DettaglioEsercizio dettaglio;
    public Boolean isChecked;

    public Esercizio() {
    }

    public Esercizio(DettaglioEsercizio dettaglio) {
        this.dettaglio = dettaglio;
    }

    public Esercizio(String nome, DettaglioEsercizio dettaglio) {
        this.nome = nome;
        this.dettaglio = dettaglio;
        this.isChecked = false;
    }

    public Esercizio(String nome, String parteDelCorpo, DettaglioEsercizio dettaglio) {
        this.nome = nome;
        this.parteDelCorpo = parteDelCorpo;
        this.dettaglio = dettaglio;
        this.isChecked = false;
    }

    public Esercizio(String nome, String parteDelCorpo) {
        this.nome = nome;
        this.parteDelCorpo = parteDelCorpo;
        this.isChecked = false;
    }

    public Esercizio(String nome, String descrizione, String difficolta, String parteDelCorpo, String tipologia, String esecuzione) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.parteDelCorpo = parteDelCorpo;
        this.tipologia = tipologia;
        this.difficolta = difficolta;
        this.esecuzione = esecuzione;
        this.isChecked = false;
    }

    public Esercizio(String nome, String descrizione, String parteDelCorpo, String tipologia, String difficolta, String esecuzione, DettaglioEsercizio dettaglio) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.parteDelCorpo = parteDelCorpo;
        this.tipologia = tipologia;
        this.difficolta = difficolta;
        this.esecuzione = esecuzione;
        this.dettaglio = dettaglio;
        this.isChecked = false;
    }

    public String getId() {return this.nome.replace(" ","");}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getParteDelCorpo() {
        return parteDelCorpo;
    }

    public void setParteDelCorpo(String parteDelCorpo) {
        this.parteDelCorpo = parteDelCorpo;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getDifficolta() {
        return difficolta;
    }

    public void setDifficolta(String difficolta) {
        this.difficolta = difficolta;
    }

    public DettaglioEsercizio getDettaglio() {
        return dettaglio;
    }

    public void setDettaglio(DettaglioEsercizio dettaglio) {
        this.dettaglio = dettaglio;
    }

    public String getEsecuzione() {
        return esecuzione;
    }

    public void setEsecuzione(String esecuzione) {
        this.esecuzione = esecuzione;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIschecked(boolean ischecked) {
        this.isChecked = ischecked;
    }

}
