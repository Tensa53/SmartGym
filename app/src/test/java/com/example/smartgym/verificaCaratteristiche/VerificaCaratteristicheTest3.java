package com.example.smartgym.verificaCaratteristiche;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class VerificaCaratteristicheTest3 {

    @Test
    public void testCaratteristiche() {
        Integer peso = 60;
        Integer altezza = 110;
        Integer numeroAllenamenti = 4;

        VerificaCaratteristicheMethod verificaCaratteristicheMethod = new VerificaCaratteristicheMethod();

        assertFalse(verificaCaratteristicheMethod.verificaCaratteristiche(peso,altezza,numeroAllenamenti));
    }

}
