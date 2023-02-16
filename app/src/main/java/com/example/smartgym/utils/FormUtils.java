package com.example.smartgym.utils;

import com.example.smartgym.gestioneScheda.application.exception.ExcersisesDurationExceededException;
import com.example.smartgym.gestioneScheda.application.exception.ExercisesRepsExceededException;
import com.example.smartgym.gestioneScheda.application.exception.NumberExercsisesExceededException;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;
import com.example.smartgym.infoUtenti.application.exception.AthleteFeaturesFieldException;
import com.example.smartgym.infoUtenti.application.exception.LoginFieldException;
import com.example.smartgym.infoUtenti.application.exception.RegisterFieldException;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La classe FormUtils si occupa di verificare i form presenti nel nostro sistema e validare gli input ricevuti. Nel caso di valori non ammissibili saranno sollevate
 * le adeguate eccezioni da controllare nella classe chiamante
 */
public class FormUtils {

    /**
     * Controlla i che i valori inseriti nei campi relativi ad Email e Password siano validi. Il seguente metodo viene usato
     * sui form di login e registrazione
     *
     * @param email, l'email dell'utente
     * @param password, la password dell'utente
     * @param ripetiPassword, la password ripetuta
     * @throws LoginFieldException
     */
    public void controllaEmailEPassword(String email, String password, String ripetiPassword) throws LoginFieldException {
        if (email.length() == 0)
            throw new LoginFieldException("etEmail_Inserisci un indirizzo email");

        if (email.length() > 40)
            throw new LoginFieldException("etEmail_L'indirizzo email non deve superare i 40 caratteri");

        Pattern pattern = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches())
            throw new LoginFieldException("etEmail_L'indirizzo email deve rispettare il formato");

        if (password.length() == 0)
            throw new LoginFieldException("etPassword_Inserisci una password");

        if (password.length() > 14)
            throw new LoginFieldException("etPassword_La password non deve superare i 14 caratteri");

        if (ripetiPassword != null){
            if (password.compareTo(ripetiPassword) != 0)
                throw new LoginFieldException("etPassword_Le password non corrispondono");
        }

        Pattern pattern1 = Pattern.compile("(?=.*[!@#$%^&*])(?=.*\\d)(?=.*[A-Z]).{8,}");
        Matcher matcher1 = pattern1.matcher(password);

        if (!matcher1.matches())
            throw new LoginFieldException("etPassword_La password deve avere almeno 8 caratteri, di cui uno maiuscolo,un numero, un carattere speciale");
    }

    /**
     * Controlla che i restanti valori inseriti nei campi del form di registrazione siano validi
     *
     * @param nome, il nome dell'utente
     * @param cognome, il cognome dell'utente
     * @param dataDiNascita, la data di nascita dell'utente
     * @param selectedRadio, l'id del radio button selezionato
     * @throws RegisterFieldException
     */
    public void controllaAltriCampiRegistrazione(String nome,String cognome,String dataDiNascita,int selectedRadio) throws RegisterFieldException {
        if (nome.length() == 0)
            throw new RegisterFieldException("etNome_Inserire un nome !!!");

        if (nome.length() > 20)
            throw new RegisterFieldException("etNome_Il nome non deve superare i 20 caratteri !!!");

        if (cognome.length() == 0)
            throw new RegisterFieldException("etCognome_Inserire un cognome !!!");

        if (cognome.length() > 20)
            throw new RegisterFieldException("etCognome_Il cognome non deve superare i 20 caratteri !!!");

        if (dataDiNascita.compareTo("DD-MM-YYYY") == 0)
            throw new RegisterFieldException("tvDatadiNascita_Inserire una data di nascita !!!");

        if (selectedRadio == -1)
            throw new RegisterFieldException("rbSesso_Selezionare il sesso !!!");
    }

    /**
     * Controlla che i valori inseriti nei campi relativi alla creazione della scheda esercizi siano validi
     *
     * @param schedaEsercizi, una raccolta di esercizi
     * @throws NumberExercsisesExceededException
     * @throws ExercisesRepsExceededException
     * @throws ExcersisesDurationExceededException
     */
    public void controllaListaEsercizi(List<Esercizio> schedaEsercizi) throws NumberExercsisesExceededException, ExercisesRepsExceededException, ExcersisesDurationExceededException {
        if (schedaEsercizi.size() < 3 || schedaEsercizi.size() > 10)
            throw new NumberExercsisesExceededException();

        for (Esercizio e: schedaEsercizi) {
            Integer reps = e.getDettaglio().getRipetizioni();
            Integer time = e.getDettaglio().getDurata();

            if (time == -1){
                if (reps > 0) {
                    if (reps < 3 || reps > 30)
                        throw new ExercisesRepsExceededException();
                } else
                    throw new ExercisesRepsExceededException();
            }

            if (reps == -1) {
                if (time > 0) {
                    if (time < 20 || time > 60)
                        throw new ExcersisesDurationExceededException();
                } else
                    throw new ExcersisesDurationExceededException();
            }
        }
    }

    /**
     * Controlla che i valori inseriti nei campi relativi all'inserimento e/o modifica caratteristiche siano validi
     *
     * @param peso, il peso dell'atleta
     * @param altezza, l'altezza dell'atleta
     * @param numAllenamenti, gli allenamenti dell'atleta
     * @throws AthleteFeaturesFieldException
     */
    public void controllaCaratteristicheAtleta(Integer peso, Integer altezza, Integer numAllenamenti) throws AthleteFeaturesFieldException {

        if (peso == 0)
            throw new AthleteFeaturesFieldException("etPeso_Inserire un valore per il peso");

        if (peso < 40 || peso > 300)
            throw new AthleteFeaturesFieldException("etPeso_Il peso deve essere compreso tra 40 e 300 kg");

        if (altezza == 0)
            throw new AthleteFeaturesFieldException("etAltezza_Inserire un valore per l'altezza");

        if (altezza < 130 || altezza > 230)
            throw new AthleteFeaturesFieldException("etAltezza_L'altezza deve essere compresa tra 130 e 230 cm");

        if (numAllenamenti == 0)
            throw new AthleteFeaturesFieldException("etAllenamenti_Inserire un valore per gli allenamenti");

        if (numAllenamenti < 2 || numAllenamenti > 7)
            throw new AthleteFeaturesFieldException("etAllenamenti_Gli allenamenti devono essere compresi tra 2 e 7");

    }

    /**
     * Calcola la dataDiNascita a partire dalla stringa ricevuta in input
     *
     * @param dataDiNascita, la data di nascita dell'utente
     * @return Date, un'istanza della classe che si occupa di gestire le date
     */
    public Date calcolaDataDiNascita(String dataDiNascita) {
        int day = Integer.parseInt(dataDiNascita.split("-")[0]);

        int month = Integer.parseInt(dataDiNascita.split("-")[1]);

        int year = Integer.parseInt(dataDiNascita.split("-")[2]);

        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, day);

        Date date = new Date(gregorianCalendar.getTimeInMillis());

        return date;
    }

    /**
     * Calcola la data massima da poter inserire tale che l'età sia 18 anni
     *
     * @return Long, il valore della data massima in millisecondi
     */
    public long getMaxDate() {
        long yearTime;

        long yearTimeTot = 0L;

        int yeard = Calendar.getInstance().get(Calendar.YEAR);

        for (int i = yeard; i > (yeard - 18); i--){
            if (i % 4 == 0)
                yearTime = 1000L*60*60*24*366;
            else
                yearTime = 1000L*60*60*24*365;

            yearTimeTot += yearTime;
        }

        return yearTimeTot;
    }
}
