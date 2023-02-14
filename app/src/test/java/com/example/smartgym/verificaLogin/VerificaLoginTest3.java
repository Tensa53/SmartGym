package com.example.smartgym.verificaLogin;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;

import org.junit.Test;
import org.mockito.Mockito;

public class VerificaLoginTest3 {

    @Test
    public void testLogin() {

        String email = "giuseppeverdi@mail.it";
        String password = "OkayMyPass1@";

        LoginRegistration loginRegistration = Mockito.mock(LoginRegistration.class);

        when(loginRegistration.login(email, password)).thenReturn(new LoginTask(true));

        assertTrue(loginRegistration.login(email,password).isSuccessful());

    }

}
