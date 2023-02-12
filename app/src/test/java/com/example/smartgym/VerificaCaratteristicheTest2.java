package com.example.smartgym;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class VerificaCaratteristicheTest2 {

    @Test
    public void testCaratteristiche() {
        Integer peso = 30;
        Integer altezza = 150;
        Integer numeroAllenamenti = 4;

        VerificaCaratteristicheMethod verificaCaratteristicheMethod = new VerificaCaratteristicheMethod();

        assertFalse(verificaCaratteristicheMethod.verificaCaratteristiche(peso,altezza,numeroAllenamenti));
    }

}
