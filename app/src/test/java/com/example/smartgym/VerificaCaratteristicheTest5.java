package com.example.smartgym;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class VerificaCaratteristicheTest5 {

    @Test
    public void testCaratteristiche() {
        Integer peso = 70;
        Integer altezza = 160;
        Integer numeroAllenamenti = 1;

        VerificaCaratteristicheMethod verificaCaratteristicheMethod = new VerificaCaratteristicheMethod();

        assertFalse(verificaCaratteristicheMethod.verificaCaratteristiche(peso,altezza,numeroAllenamenti));
    }

}
