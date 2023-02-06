package com.example.smartgym.infoUtenti.application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartgym.R;
import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;
import com.example.smartgym.infoUtenti.storage.entity.Atleta;

public class ModificaCaratteristicheActivity extends AppCompatActivity implements View.OnClickListener {

    Button btUpdate, btReturn;
    TextView weight, height, experience, numAllenamenti;
    Atleta myAthlete;
    LoginRegistration loginRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_caratteristiche);

        loginRegistration = new LoginRegistration();

        Bundle bundle = getIntent().getExtras();

        myAthlete = (Atleta) (bundle.getSerializable("User"));

        btUpdate = findViewById(R.id.btUpdate);
        btReturn = findViewById(R.id.btReturn);

        weight = findViewById(R.id.weightValue);
        weight.setText(myAthlete.getPeso() + "");
        height = findViewById(R.id.heightValue);
        height.setText(myAthlete.getAltezza() + "");
        experience = findViewById(R.id.experienceValue);
        experience.setText(myAthlete.getEsperienza() + "");
        numAllenamenti = findViewById(R.id.numAllenamenti);
        numAllenamenti.setText(myAthlete.getAllenamentiSettimanali() + "");

        btUpdate.setOnClickListener(this);
        btReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btUpdate: onUpdate();
            case R.id.btReturn: returnProfile();
        }
    }

    private void onUpdate(){

    }

    private void returnProfile(){
        super.onBackPressed();
    }
}