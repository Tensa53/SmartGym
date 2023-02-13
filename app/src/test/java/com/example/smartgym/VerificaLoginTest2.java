package com.example.smartgym;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;

import org.junit.Test;
import org.mockito.Mockito;

public class VerificaLoginTest2 {

    @Test
    public void testLogin() {

        String email = "michelespinelli46@gmail.com";
        String password = "passwordErrata";

        LoginRegistration loginRegistration = Mockito.mock(LoginRegistration.class);

        when(loginRegistration.login(email, password)).thenReturn(new LoginTask(false));

        assertFalse(loginRegistration.login(email,password).isSuccessful());

    }

}
