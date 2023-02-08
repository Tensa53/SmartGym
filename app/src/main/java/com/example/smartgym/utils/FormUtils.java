package com.example.smartgym.utils;

import com.example.smartgym.gestioneScheda.application.exception.ExcersisesDurationExceededException;
import com.example.smartgym.gestioneScheda.application.exception.ExercisesRepsExceededException;
import com.example.smartgym.gestioneScheda.application.exception.NumberExercsisesExceededException;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;
import com.example.smartgym.infoUtenti.application.exception.AthleteFeaturesFieldException;
import com.example.smartgym.infoUtenti.application.exception.LoginFieldException;
import com.example.smartgym.infoUtenti.application.exception.RegisterFieldException;
import com.google.firebase.Timestamp;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormUtils {

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

    public void controllaListaEsercizi(List<Esercizio> schedaEsercizi) throws NumberExercsisesExceededException, ExercisesRepsExceededException, ExcersisesDurationExceededException {
        if (schedaEsercizi.size() < 3 || schedaEsercizi.size() > 10)
            throw new NumberExercsisesExceededException();

        for (Esercizio e: schedaEsercizi) {
            Integer reps = e.getDettaglio().getRipetizioni();
            Integer time = e.getDettaglio().getRipetizioni();

            if (reps != null){
                if (reps < 3 || reps > 30)
                    throw new ExercisesRepsExceededException();
            }

            if (time != null) {
                if (time < 20 || time > 60)
                    throw new ExcersisesDurationExceededException();
            }
        }
    }

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

    public Date calcolaDataDiNascita(String dataDiNascita) {
        int day = Integer.parseInt(dataDiNascita.split("-")[0]);

        int month = Integer.parseInt(dataDiNascita.split("-")[1]);

        int year = Integer.parseInt(dataDiNascita.split("-")[2]);

        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, day);

        Date date = new Date(gregorianCalendar.getTimeInMillis());

        return date;
    }

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
