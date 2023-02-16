package com.example.smartgym.infoUtenti.application.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartgym.R;
import com.example.smartgym.start.MainActivity;
import com.example.smartgym.infoUtenti.application.exception.LoginFieldException;
import com.example.smartgym.utils.FormUtils;
import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * Questa classe rappresenta l'activity che si occupa di effettuare le operazioni relative al
 * login. Implementa l'interfaccia View.OnClickListener
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail,etPassword;

    Button btLogin,btRegistrazione;

    LoginRegistration loginRegistration;

    FormUtils formUtils;

    /**
     * Metodo di callback chiamato quando l'Activity viene creata.
     * In esso sono richiamati i relativi metodi per istanziare i widget.
     * Inoltre sono settati i listener per i pulsanti
     *
     * @param savedInstanceState oggetto Bundle contenente lo stato dell'Activity in caso di riavvio
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        widgetBinding();

        btLogin.setOnClickListener(this);
        btRegistrazione.setOnClickListener(this);

        formUtils = new FormUtils();
        loginRegistration = new LoginRegistration();
    }

    /**
     * Questo metodo gestisce l'evento di click del pulsante specificato.
     *
     * @param view la vista che ha generato l'evento di click
     */
    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btLogin: onLogin();
                break;
            case R.id.btRegistrazione: onRegister();
                break;
        }
    }

    /**
     * Questo metodo viene richiamato da onClick() per lanciare l'activity che si occupa della
     * registrazione di un nuovo utente
     */
    private void onRegister() {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * Questo metodo viene richiamato da onClick() per effettuare le operazioni relative al login.
     * Viene gestita l'eccezione LoginFielException
     */
    private void onLogin() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        try {
            formUtils.controllaEmailEPassword(email,password,null);

            Task<AuthResult> loginResult = loginRegistration.login(email,password);

            loginResult.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        launchHome();
                    } else {
                        etEmail.setError("Credenziali Errate !!!");
                        etPassword.setError("Credenziali Errate !!!");
                    }
                }
            });
        } catch (LoginFieldException e) {
            showError(e.getMessage());
        }
    }

    /**
     * Questo metodo viene richiamato da onLogin() per mostrare un messaggio di errore quando uno
     * dei valori inserito nei campi del form nom viene validato
     * @param error, rappresenta il messaggio di errore
     */
    private void showError(String error) {
        String id = error.split("_")[0];
        String msg = error.split("_")[1];

        int rid = getResources().getIdentifier(id, "id", getPackageName());

        EditText et = findViewById(rid);

        et.setError(msg);
    }

    /**
     * Questo metodo viene richiamato da onLogin() quando la validazione dei campi del form va a
     * buon e viene quindi lanciata l'activity home
     */
    private void launchHome() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Metodo che viene chiamato per effettuare il binding dei widget del layout
     * con le rispettive istanze della classe a cui appartengono
     */
    private void widgetBinding() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        btRegistrazione = findViewById(R.id.btRegistrazione);
    }
}