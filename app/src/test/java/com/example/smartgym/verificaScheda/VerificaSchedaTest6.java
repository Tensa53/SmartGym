package com.example.smartgym.verificaScheda;

import static org.junit.Assert.assertFalse;

import com.example.smartgym.gestioneScheda.storage.entity.DettaglioEsercizio;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;

import org.junit.Test;

import java.util.ArrayList;

public class VerificaSchedaTest6 {

    @Test
    public void testScheda() {

        ArrayList<Esercizio> schedaEsercizi = new ArrayList<Esercizio>();

        Esercizio esercizio1 = new Esercizio("esercizio1", new DettaglioEsercizio(25, -1));
        schedaEsercizi.add(esercizio1);
        Esercizio esercizio2 = new Esercizio("esercizio2", new DettaglioEsercizio(-1, 40));
        schedaEsercizi.add(esercizio2);
        Esercizio esercizio3 = new Esercizio("esercizio3", new DettaglioEsercizio(12, -1));
        schedaEsercizi.add(esercizio1);
        Esercizio esercizio4 = new Esercizio("esercizio4", new DettaglioEsercizio(-1, 25));
        schedaEsercizi.add(esercizio2);
        Esercizio esercizio5 = new Esercizio("esercizio5", new DettaglioEsercizio(20, -1));
        schedaEsercizi.add(esercizio1);
        Esercizio esercizio6 = new Esercizio("esercizio6", new DettaglioEsercizio(-1, 20));
        schedaEsercizi.add(esercizio2);
        Esercizio esercizio7 = new Esercizio("esercizio7", new DettaglioEsercizio(10, -1));
        schedaEsercizi.add(esercizio1);
        Esercizio esercizio8 = new Esercizio("esercizio8", new DettaglioEsercizio(14, -1));
        schedaEsercizi.add(esercizio2);
        Esercizio esercizio9 = new Esercizio("esercizio9", new DettaglioEsercizio(18, -1));
        schedaEsercizi.add(esercizio1);
        Esercizio esercizio10 = new Esercizio("esercizio10", new DettaglioEsercizio(10, -1));
        schedaEsercizi.add(esercizio2);
        Esercizio esercizio11 = new Esercizio("esercizio11", new DettaglioEsercizio(30, -1));
        schedaEsercizi.add(esercizio1);

        VerificaSchedaMethod verificaSchedaMethod = new VerificaSchedaMethod();

        assertFalse(verificaSchedaMethod.verificaScheda(schedaEsercizi));
    }

}
