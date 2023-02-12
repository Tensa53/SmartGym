package com.example.smartgym.verificaScheda;

import static org.junit.Assert.assertTrue;

import com.example.smartgym.gestioneScheda.storage.entity.DettaglioEsercizio;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;

import org.junit.Test;

import java.util.ArrayList;

public class VerificaSchedaTest7 {

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

        VerificaSchedaMethod verificaSchedaMethod = new VerificaSchedaMethod();

        assertTrue(verificaSchedaMethod.verificaScheda(schedaEsercizi));
    }

}
