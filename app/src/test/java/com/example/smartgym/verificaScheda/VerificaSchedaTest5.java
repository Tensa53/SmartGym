package com.example.smartgym.verificaScheda;

import static org.junit.Assert.assertFalse;

import com.example.smartgym.gestioneScheda.storage.entity.DettaglioEsercizio;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;

import org.junit.Test;

import java.util.ArrayList;

public class VerificaSchedaTest5 {

    @Test
    public void testScheda() {

        ArrayList<Esercizio> schedaEsercizi = new ArrayList<Esercizio>();

        Esercizio esercizio1 = new Esercizio("esercizio1", new DettaglioEsercizio(25, -1));
        schedaEsercizi.add(esercizio1);
        Esercizio esercizio2 = new Esercizio("esercizio2", new DettaglioEsercizio(-1, 40));
        schedaEsercizi.add(esercizio2);

        VerificaSchedaMethod verificaSchedaMethod = new VerificaSchedaMethod();

        assertFalse(verificaSchedaMethod.verificaScheda(schedaEsercizi));
    }

}
