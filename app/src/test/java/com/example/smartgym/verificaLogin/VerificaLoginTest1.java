package com.example.smartgym.verificaLogin;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;

import org.junit.Test;
import org.mockito.Mockito;

public class VerificaLoginTest1 {

    @Test
    public void testLogin() {

        String email = "genericmail@notmail.it";
        String password = "AnyPass1@";

        LoginRegistration loginRegistration = Mockito.mock(LoginRegistration.class);

        when(loginRegistration.login(email, password)).thenReturn(new LoginTask(false));

        assertFalse(loginRegistration.login(email,password).isSuccessful());

    }

}
