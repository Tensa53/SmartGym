package com.example.smartgym.verificaCaratteristiche;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VerificaCaratteristicheTest7 {

    @Test
    public void testCaratteristiche() {
        Integer peso = 80;
        Integer altezza = 190;
        Integer numeroAllenamenti = 5;

        VerificaCaratteristicheMethod verificaCaratteristicheMethod = new VerificaCaratteristicheMethod();

        assertTrue(verificaCaratteristicheMethod.verificaCaratteristiche(peso,altezza,numeroAllenamenti));
    }

}
