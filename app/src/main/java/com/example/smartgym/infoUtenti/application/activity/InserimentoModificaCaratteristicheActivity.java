package com.example.smartgym.infoUtenti.application.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.smartgym.R;
import com.example.smartgym.infoUtenti.application.exception.AthleteFeaturesFieldException;
import com.example.smartgym.infoUtenti.application.logic.AthleteInfo;
import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;
import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.example.smartgym.start.MainActivity;
import com.example.smartgym.utils.FormUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class InserimentoModificaCaratteristicheActivity extends AppCompatActivity implements View.OnClickListener {

    Button btUpdate, btReturn;
    EditText etPeso, etAltezza, etAllenamenti;
    Spinner spinnerEsperienza;

    LoginRegistration loginRegistration;
    AthleteInfo athleteInfo;
    FormUtils formUtils;
    Atleta myAthlete;

    ArrayAdapter<String> adapterItems;
    String[] itemsExperience = {"Principiante","Intermedio","Esperto"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserimento_modifica_caratteristiche);

        loginRegistration = new LoginRegistration();
        Bundle bundle = getIntent().getExtras();
        myAthlete = (Atleta) (bundle.getSerializable("User"));

        athleteInfo = new AthleteInfo();

        formUtils = new FormUtils();

        widgetBinding();

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_experience, itemsExperience);

        spinnerEsperienza.setAdapter(adapterItems);

        setFields();

        spinnerEsperienza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) adapterView.getItemAtPosition(i);
                myAthlete.setEsperienza(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btUpdate.setOnClickListener(this);
        btReturn.setOnClickListener(this);
    }

    private void setFields() {
        spinnerEsperienza.setSelection(myAthlete.esperienzaValue());

        etPeso.setText("" + myAthlete.getPeso());

        etAltezza.setText("" + myAthlete.getAltezza());

        etAllenamenti.setText("" + myAthlete.getAllenamentiSettimanali());

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btUpdate: onUpdate();
            break;
            case R.id.btReturn: returnProfile();
            break;
        }
    }

    private void widgetBinding() {
        btUpdate = findViewById(R.id.btUpdate);
        btReturn = findViewById(R.id.btReturn);
        etPeso = findViewById(R.id.etPeso);
        etAltezza = findViewById(R.id.etAltezza);
        etAllenamenti = findViewById(R.id.etAllenamenti);
        spinnerEsperienza = findViewById(R.id.spinnerEsperienza);
    }

    private void onUpdate(){
        Integer peso = Integer.valueOf(etPeso.getText().toString());
        Integer altezza = Integer.valueOf(etAltezza.getText().toString());
        Integer numeroAllenamenti = Integer.valueOf(etAllenamenti.getText().toString());
        String esperienza = spinnerEsperienza.getSelectedItem().toString();

        if (verificaCaratteristiche(peso, altezza, numeroAllenamenti)) {
            updateCaratteristicheAtleta(peso, altezza, numeroAllenamenti, esperienza);
        }
    }

    private void updateCaratteristicheAtleta(Integer peso, Integer altezza, Integer numeroAllenamenti, String esperienza) {
        myAthlete.setPeso(peso);
        myAthlete.setAltezza(altezza);
        myAthlete.setAllenamentiSettimanali(numeroAllenamenti);
        myAthlete.setEsperienza(esperienza);

        Task <Void> updateResult = athleteInfo.editAthleteFeatures(myAthlete, loginRegistration.getUserLogged().getUid());

        updateResult.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                mostraAvviso();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void mostraAvviso() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE:
                        lanciaHome();
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Caratteristiche aggiornate con successo !")
                .setPositiveButton("OK", dialogClickListener).show();
    }

    private boolean verificaCaratteristiche(Integer peso, Integer altezza, Integer numeroAllenamenti) {
        try {
            formUtils.controllaCaratteristicheAtleta(peso, altezza, numeroAllenamenti);
        } catch (AthleteFeaturesFieldException e) {
            showError(e.getMessage());
            return false;
        }

        return true;
    }

    private void showError(String error) {
        String id = error.split("_")[0];
        String msg = error.split("_")[1];

        if (id.substring(0,2).equals("et")){
            int rid = getResources().getIdentifier(id, "id", getPackageName());

            EditText et = findViewById(rid);

            et.setError(msg);
        } else
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

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