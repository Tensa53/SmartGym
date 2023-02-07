package com.example.smartgym.infoUtenti.application.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartgym.R;
import com.example.smartgym.infoUtenti.application.logic.AthleteInfo;
import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;
import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.example.smartgym.start.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

public class ModificaInfoAtletaActivity extends AppCompatActivity implements View.OnClickListener {

    Button btUpdate, btReturn;
    TextView name, surname, email, password;
    Atleta myAthlete;
    LoginRegistration loginRegistration = new LoginRegistration();
    AthleteInfo atletaInfo = new AthleteInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_info_atleta);

        Bundle bundle = getIntent().getExtras();
        myAthlete = (Atleta)(bundle.getSerializable("User"));

        btUpdate = findViewById(R.id.btUpdate);
        btReturn = findViewById(R.id.btReturn);

        name = findViewById(R.id.nameValue);
        name.setText(myAthlete.getNome());
        surname = findViewById(R.id.surnameValue);
        surname.setText(myAthlete.getCognome());
        email = findViewById(R.id.emailValue);
        email.setText(myAthlete.getEmail());
        password = findViewById(R.id.passwordValue);

        btUpdate.setOnClickListener(this);
        btReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        String idUser = loginRegistration.getUserLogged().getUid();

        switch (id){
            case R.id.btUpdate: onUpdate(myAthlete,idUser);
                break;
            case R.id.btReturn: returnProfile();
                break;
        }
    }

    private void onUpdate(Atleta atleta, String id) {

        String nuovoNome = name.getText().toString();
        String nuovoCognome = surname.getText().toString();
        String nuovaEmail = email.getText().toString();

        myAthlete.setNome(nuovoNome);
        myAthlete.setCognome(nuovoCognome);
        myAthlete.setEmail(nuovaEmail);

        Task<Void> updateResult = atletaInfo.editAthleteInfo(atleta,id);

        updateResult.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getBaseContext(), "Informazioni aggiornate con successo !", Toast.LENGTH_LONG).show();
                lanciaHome();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void lanciaHome() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void returnProfile(){
        super.onBackPressed();
    }
}