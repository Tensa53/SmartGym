package com.example.smartgym.infoUtenti.application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartgym.R;
import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;
import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.google.firebase.auth.FirebaseUser;

public class ModificaInfoAtletaActivity extends AppCompatActivity implements View.OnClickListener {

    Button btUpdate, btReturn;
    TextView name, surname, email, password;
    Atleta myAthlete;
    LoginRegistration loginRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_info_atleta);

        loginRegistration = new LoginRegistration();
        FirebaseUser user = loginRegistration.getUserLogged();

        btUpdate = findViewById(R.id.btUpdate);
        btReturn = findViewById(R.id.btReturn);

        name = findViewById(R.id.nameValue);
        surname = findViewById(R.id.surnameValue);
        email = findViewById(R.id.emailValue);
        password = findViewById(R.id.passwordValue);

        btUpdate.setOnClickListener(this);
        btReturn.setOnClickListener(this);
    }

    private void saveAtleta(Atleta atleta) {
        myAthlete = atleta;

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.btUpdate: onUpdate();
            case R.id.btReturn: returnProfile();
        }
    }

    private void onUpdate() {

    }

    private void returnProfile(){

    }
}