package com.example.smartgym;

import static org.junit.Assert.assertFalse;

import com.example.smartgym.gestioneScheda.storage.entity.DettaglioEsercizio;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class VerificaSchedaTest1 {

    @Test
    public void testScheda() {

        ArrayList<Esercizio> schedaEsercizi = new ArrayList<Esercizio>();

        Esercizio esercizio1 = new Esercizio("esercizio1","braccia",new DettaglioEsercizio(10,null));
        schedaEsercizi.add(esercizio1);
        Esercizio esercizio2 = new Esercizio("esercizio2","petto",new DettaglioEsercizio(7,null));
        schedaEsercizi.add(esercizio2);

        VerificaSchedaMethod verificaSchedaMethod = new VerificaSchedaMethod();

        assertFalse(verificaSchedaMethod.verificaScheda(schedaEsercizi));
    }

}
