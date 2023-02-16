package com.example.smartgym.infoUtenti.storage.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * La classe atleta rappresenta un utente atleta che utilizza il sistema.
 * Un atleta ha un nome, cognome, email, sesso, data di nascita che rappresentano le informazioni
 * anagrafiche e personali.
 * Inoltre altezza, peso, esperienza e allenamenti settimanali rappresentano le caratteristiche
 * dell'atletta.
 */
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

    /**
     * Costruttore vuoto
     */
    public Atleta() {

    }

    /**
     * Costruttore con nome, cognome, email, sesso, dataDiNascita
     *
     * @param nome, nome dell'atleta
     * @param cognome, cognome dell'atleta
     * @param email, email dell'atleta
     * @param sesso, sesso dell'atleta
     * @param dataDiNascita data di nascita dell'atleta
     */
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

    /**
     * Verifica se sono state inserite le caratteristiche dell'atleta
     *
     * @return boolean, true/false a seconda del valore dei campi
     */
    public boolean areFeaturesEmpty(){
        boolean res = this.altezza == 0 && this.peso == 0 && this.allenamentiSettimanali == 0;

        return res;
    }

    /**
     * Restitsuisce il numero di allenamenti settimanali
     *
     * @return int, un intero che rappresenta il numero di allenamenti settimanali
     */
    public int getAllenamentiSettimanali() {
        return allenamentiSettimanali;
    }

    /**
     * Imposta il numero di allenamenti settimanali
     *
     * @param allenamentiSettimanali, un intero (min. 2, max. 7)
     */
    public void setAllenamentiSettimanali(int allenamentiSettimanali) {
        this.allenamentiSettimanali = allenamentiSettimanali;
    }

    /**
     * Restituisce l'esperienza dell'atleta
     *
     * @return String, una stringa che rappresenta l'esperienza
     */
    public String getEsperienza() {
        return esperienza;
    }

    /**
     * Restituisce l'esperienza come un intero
     *
     * @return int, un intero che rappresenta l'esperienza
     */
    public Integer esperienzaValue() {
        if (esperienza.equalsIgnoreCase("Principiante"))
            return 0;

        if (esperienza.equalsIgnoreCase("Intermedio"))
            return 1;

        if (esperienza.equalsIgnoreCase("Esperto"))
            return 2;

        return 0;
    }

    /**
     * Imposta l'esperienza dell'atleta
     *
     * @param esperienza, una stringa che rappresenta l'esperienza dell'atleta
     */
    public void setEsperienza(String esperienza) {
        this.esperienza = esperienza;
    }

    /**
     * Restituisce il nome dell'atleta
     *
     * @return String, una stringa che rappresenta il nome dell'atleta
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome dell'atleta
     *
     * @param nome, una stringa che rappresenta il nome dell'atleta
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il cognome dell'atleta
     *
     * @return String, una stringa che rappresenta il cognome dell'atleta
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il cognome dell'atleta
     *
     * @param cognome, una stringa che rappresenta il cognome dell'atleta
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Restituisce l'email dell'atleta
     *
     * @return String, una stringa che rappresenta l'email dell'atleta
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta l'email dell'atleta
     *
     * @param email, una stringa che rappresenta l'email dell'atleta
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Restituisce il sesso dell'atleta
     *
     * @return String, una stringa che rappresenta il sesso dell'atleta
     */
    public String getSesso() {
        return sesso;
    }

    /**
     * Imposta il nome dell'atleta
     *
     * @param sesso, una stringa che rappresenta il sesso dell'atleta
     */
    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    /**
     * Restituisce la data di nascita dell'atleta
     *
     * @return Date, rappresenta la data di nascita
     */
    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     * Restituisce la data di nascita come una stringa
     *
     * @return String, una stringa che rappresenta la data di nascita
     */
    public String formattedDataDiNascita() {
        SimpleDateFormat dateFormat = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        }

        return dateFormat.format(getDataDiNascita());
    }

    /**
     * Imposta la data di nascita dell'atleta
     *
     * @param dataDiNascita, rappresenta la data di nascita
     */
    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    /**
     * Restituisce l'altezza dell'atleta
     *
     * @return int, un intero che rappresenta l'altezza
     */
    public int getAltezza() {
        return altezza;
    }

    /**
     * Imposta l'altezza dell'atleta
     *
     * @param altezza, un intero che rappresenta l'altezza
     */
    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }

    /**
     * Restituisce il peso dell'atleta
     *
     * @return int, un intero che rappresenta il peso
     */
    public int getPeso() {
        return peso;
    }

    /**
     * Imposta il peso dell'atleta
     *
     * @param peso, un intero che rappresenta il peso
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /**
     * Restituisce la stringa che mostra tutti i parametri e valori dell'istanza della classe Atleta
     *
     * @return String, una stringa con tutti i parametri e i valori
     */
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
}
