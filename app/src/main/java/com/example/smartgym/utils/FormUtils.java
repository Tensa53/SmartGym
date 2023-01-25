package com.example.smartgym.utils;

import com.example.smartgym.infoUtenti.application.exception.LoginFieldException;
import com.example.smartgym.infoUtenti.application.exception.RegisterFieldException;
import com.google.firebase.Timestamp;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormUtils {

    public void controllaAltriCampiRegistrazione(String nome,String cognome,String dataDiNascita,int selectedRadio) throws RegisterFieldException {
        if (nome.length() == 0)
            throw new RegisterFieldException("Inserire un nome !!!");

        if (nome.length() > 20)
            throw new RegisterFieldException("Il nome non deve superare i 20 caratteri !!!");

        if (cognome.length() == 0)
            throw new RegisterFieldException("Inserire un cognome !!!");

        if (cognome.length() > 20)
            throw new RegisterFieldException("Il cognome non deve superare i 20 caratteri !!!");

        if (dataDiNascita.compareTo("DD-MM-YYYY") == 0)
            throw new RegisterFieldException("Inserire una data di nascita !!!");

        if (selectedRadio == -1)
            throw new RegisterFieldException("Selezionare il sesso !!!");
    }

    public void controllaEmailEPassword(String email, String password, String ripetiPassword) throws LoginFieldException {
        if (email.length() == 0)
            throw new LoginFieldException("Inserisci un indirizzo email");

        if (email.length() > 40)
            throw new LoginFieldException("L'indirizzo email non deve superare i 40 caratteri");

        Pattern pattern = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches())
            throw new LoginFieldException("L'indirizzo email deve rispettare il formato");

        if (password.length() == 0)
            throw new LoginFieldException("Inserisci una password");

        if (password.length() > 14)
            throw new LoginFieldException("La password non deve superare i 14 caratteri");

        if (ripetiPassword != null){
            if (password.compareTo(ripetiPassword) != 0)
                throw new LoginFieldException("Le password non corrispondono");
        }

        Pattern pattern1 = Pattern.compile("(?=.*[!@#$%^&*])(?=.*\\d)(?=.*[A-Z]).{8,}");
        Matcher matcher1 = pattern1.matcher(password);

        if (!matcher1.matches())
            throw new LoginFieldException("La password deve avere almeno 8 caratteri, di cui uno maiuscolo,un numero, un carattere speciale");
    }

    public Timestamp calcolaDataDiNascita(String dataDiNascita) {
        int day = Integer.parseInt(dataDiNascita.split("-")[0]);

        int month = Integer.parseInt(dataDiNascita.split("-")[1]) - 1;

        int year = Integer.parseInt(dataDiNascita.split("-")[2]);

        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, day);

        Date date = new Date(gregorianCalendar.getTimeInMillis());

        return new Timestamp(date);
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
