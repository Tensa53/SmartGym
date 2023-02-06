package com.example.smartgym.infoUtenti.application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartgym.R;

public class ModificaInfoAtletaActivity extends AppCompatActivity implements View.OnClickListener {

    Button btUpdate;
    TextView name, surname, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_info_atleta);

        btUpdate = findViewById(R.id.btUpdate);
        name = findViewById(R.id.nameValue);
        name.setEnabled(false);
        surname = findViewById(R.id.surnameValue);
        surname.setEnabled(false);
        email = findViewById(R.id.emailValue);
        email.setEnabled(false);
        password = findViewById(R.id.passwordValue);
        password.setEnabled(false);

        btUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.btUpdate: onUpdate();
        }
    }

    private void onUpdate() {
        name.setEnabled(true);
        surname.setEnabled(true);
        email.setEnabled(true);
        password.setEnabled(true);
    }
}