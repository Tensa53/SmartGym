package com.example.smartgym.infoUtenti.application.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartgym.R;
import com.example.smartgym.application.MainActivity;
import com.example.smartgym.infoUtenti.application.service.InfoUtentiServiceImpl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail,etPassword;

    Button btLogin,btRegistrazione;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        widgetBinding();

        btLogin.setOnClickListener(this);
        btRegistrazione.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

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

    private void onRegister() {
    }

    private void onLogin() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        InfoUtentiServiceImpl infoUtentiService = new InfoUtentiServiceImpl();

        Task<AuthResult> loginResult = infoUtentiService.login(email, password);

        loginResult.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    launchHome();
                } else {
                    Toast.makeText(LoginActivity.this, "Credenziali errate !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void launchHome() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void widgetBinding() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        btRegistrazione = findViewById(R.id.btRegistrazione);
    }
}