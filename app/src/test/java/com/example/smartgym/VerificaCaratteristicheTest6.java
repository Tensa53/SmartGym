package com.example.smartgym;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class VerificaCaratteristicheTest6 {

    @Test
    public void testCaratteristiche() {
        Integer peso = 80;
        Integer altezza = 190;
        Integer numeroAllenamenti = 8;

        VerificaCaratteristicheMethod verificaCaratteristicheMethod = new VerificaCaratteristicheMethod();

        assertFalse(verificaCaratteristicheMethod.verificaCaratteristiche(peso,altezza,numeroAllenamenti));
    }

}
