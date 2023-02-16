package com.example.smartgym.gestioneScheda.storage.entity;

import java.io.Serializable;

/**
 * La classe Esercizio rappresenta un esercizio fisico.
 * Un esercizio ha un nome, una descrizione, una parte del corpo su cui agisce, una tipologia, una difficoltà, una descrizione dell'esecuzione e un dettaglio sull'esecuzione.
 * Il dettaglio è rappresentato dalla classe DettaglioEsercizio.
 */
public class Esercizio implements Serializable {

    private String nome;
    private String descrizione;
    private String parteDelCorpo;
    private String tipologia;
    private String difficolta;
    private String esecuzione;
    private DettaglioEsercizio dettaglio;
    public Boolean isChecked;

    /**
     * Costruttore vuoto.
     */
    public Esercizio() {
    }

//    /**
//     * Costruttore con parametro DettaglioEsercizio.
//     *
//     * @param dettaglio il dettaglio dell'esercizio.
//     */
//    public Esercizio(DettaglioEsercizio dettaglio) {
//        this.dettaglio = dettaglio;
//    }
//
//    public Esercizio(String nome, DettaglioEsercizio dettaglio) {
//        this.nome = nome;
//        this.dettaglio = dettaglio;
//        this.isChecked = false;
//    }
//
//    /**
//     * Costruttore con nome e DettaglioEsercizio.
//     *
//     * @param nome      il nome dell'esercizio.
//     * @param dettaglio il dettaglio dell'esercizio.
//     */
//    public Esercizio(String nome, String parteDelCorpo, DettaglioEsercizio dettaglio) {
//        this.nome = nome;
//        this.parteDelCorpo = parteDelCorpo;
//        this.dettaglio = dettaglio;
//        this.isChecked = false;
//    }
//
//    /**
//     * Costruttore con nome e parte del corpo
//     *
//     * @param nome          il nome dell'esercizio.
//     * @param parteDelCorpo la parte del corpo su cui agisce l'esercizio.
//     */
//    public Esercizio(String nome, String parteDelCorpo) {
//        this.nome = nome;
//        this.parteDelCorpo = parteDelCorpo;
//        this.isChecked = false;
//    }

    /**
     * Costruttore con nome, descrizione, difficoltà, parte del corpo, tipologia, esecuzione
     *
     * @param nome          il nome dell'esercizio.
     * @param descrizione   una descrizione dell'esercizio
     * @param difficolta    la difficoltà dell'esercizio
     * @param parteDelCorpo la parte del corpo allenata dall'esercizio
     * @param tipologia     la tipologia dell'esercizio
     * @param esecuzione    l'esecuzione dell'esercizio
     */
    public Esercizio(String nome, String descrizione, String difficolta, String parteDelCorpo, String tipologia, String esecuzione) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.parteDelCorpo = parteDelCorpo;
        this.tipologia = tipologia;
        this.difficolta = difficolta;
        this.esecuzione = esecuzione;
        this.isChecked = false;
    }

    /**
     * Costruttore con nome, descrizione, difficoltà, parte del corpo, tipologia, esecuzione, dettaglio
     *
     * @param nome          il nome dell'esercizio.
     * @param descrizione   una descrizione dell'esercizio
     * @param difficolta    la difficoltà dell'esercizio
     * @param parteDelCorpo la parte del corpo allenata dall'esercizio
     * @param tipologia     la tipologia dell'esercizio
     * @param esecuzione    l'esecuzione dell'esercizio
     * @param dettaglio     il dettaglio dell'esercizio
     */
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

    /**
     * Restituisce l'id dell'esercizio.
     *
     * @return l'id dell'esercizio.
     */
    public String getId() {
        return this.nome.replace(" ", "");
    }

    /**
     * Restituisce il nome dell'esercizio.
     *
     * @return il nome dell'esercizio.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome dell'esercizio.
     *
     * @param nome il nome dell'esercizio da impostare.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce la descrizione dell'esercizio.
     *
     * @return la descrizione dell'esercizio.
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta la descrizione dell'esercizio.
     *
     * @param descrizione la descrizione dell'esercizio da impostare.
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Restituisce la parte del corpo coinvolta nell'esecuzione dell'esercizio.
     *
     * @return la parte del corpo coinvolta nell'esecuzione dell'esercizio.
     */
    public String getParteDelCorpo() {
        return parteDelCorpo;
    }

    /**
     * Imposta la parte del corpo coinvolta nell'esecuzione dell'esercizio.
     *
     * @param parteDelCorpo la parte del corpo coinvolta nell'esecuzione dell'esercizio da impostare.
     */
    public void setParteDelCorpo(String parteDelCorpo) {
        this.parteDelCorpo = parteDelCorpo;
    }

    /**
     * Restituisce la tipologia dell'esercizio.
     *
     * @return la tipologia dell'esercizio.
     */
    public String getTipologia() {
        return tipologia;
    }

    /**
     * Imposta la tipologia dell'esercizio.
     *
     * @param tipologia la tipologia dell'esercizio da impostare.
     */
    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    /**
     * Restituisce la difficoltà dell'esercizio.
     *
     * @return la difficoltà dell'esercizio.
     */
    public String getDifficolta() {
        return difficolta;
    }

    /**
     * Imposta la difficoltà dell'esercizio.
     *
     * @param difficolta la difficoltà dell'esercizio da impostare.
     */
    public void setDifficolta(String difficolta) {
        this.difficolta = difficolta;
    }

    /**
     * Restituisce i dettagli dell'esercizio.
     *
     * @return i dettagli dell'esercizio.
     */
    public DettaglioEsercizio getDettaglio() {
        return dettaglio;
    }

    /**
     * Imposta i dettagli dell'esercizio.
     *
     * @param dettaglio i dettagli dell'esercizio da impostare.
     */
    public void setDettaglio(DettaglioEsercizio dettaglio) {
        this.dettaglio = dettaglio;
    }

    /**
     * Restituisce l'esecuzione dell'esercizio.
     *
     * @return l'esecuzione dell'esercizio.
     */
    public String getEsecuzione() {
        return esecuzione;
    }

    /**
     * Imposta l'esecuzione dell'esercizio.
     *
     * @param esecuzione l'esecuzione dell'esercizio da impostare.
     */
    public void setEsecuzione(String esecuzione) {
        this.esecuzione = esecuzione;
    }

    /**
     * Restituisce lo stato di controllo dell'esercizio.
     *
     * @return lo stato di controllo dell'esercizio.
     */
    public boolean isChecked() {
        return isChecked;
    }

    /**
     * Imposta lo stato di controllo dell'esercizio.
     *
     * @param ischecked indica se l'esercizio è selezionato o meno
     */
    public void setIschecked(boolean ischecked) {
        this.isChecked = ischecked;
    }

}
