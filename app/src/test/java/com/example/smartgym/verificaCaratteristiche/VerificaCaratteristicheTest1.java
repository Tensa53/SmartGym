package com.example.smartgym.verificaCaratteristiche;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class VerificaCaratteristicheTest1 {

    @Test
    public void testCaratteristiche() {
        Integer peso = 500;
        Integer altezza = 150;
        Integer numeroAllenamenti = 4;

        VerificaCaratteristicheMethod verificaCaratteristicheMethod = new VerificaCaratteristicheMethod();

        assertFalse(verificaCaratteristicheMethod.verificaCaratteristiche(peso,altezza,numeroAllenamenti));
    }

}
