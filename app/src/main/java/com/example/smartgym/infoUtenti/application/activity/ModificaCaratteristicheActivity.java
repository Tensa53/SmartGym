package com.example.smartgym.infoUtenti.application.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
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

public class ModificaCaratteristicheActivity extends AppCompatActivity implements View.OnClickListener {

    Button btUpdate, btReturn;
    TextView weight, height, allenamenti;
    Atleta myAthlete;
    LoginRegistration loginRegistration;
    AthleteInfo athleteInfo;
    Spinner spinnerEsperienza;
    AutoCompleteTextView autoCompleteTextViewAllenamenti, autoCompleteTextViewEsperienza;
    ArrayAdapter<String> adapterItems;
    String[] itemsExperience = {"Principiante","Intermedio","Esperto"};
    String[] itemsAllenamenti = {"1","2","3","4","5","6","7"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_caratteristiche);

        loginRegistration = new LoginRegistration();
        Bundle bundle = getIntent().getExtras();
        myAthlete = (Atleta) (bundle.getSerializable("User"));

        athleteInfo = new AthleteInfo();

        btUpdate = findViewById(R.id.btUpdate);
        btReturn = findViewById(R.id.btReturn);

        spinnerEsperienza = findViewById(R.id.spinnerEsperienza);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_experience, itemsExperience);

        spinnerEsperienza.setAdapter(adapterItems);

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

        spinnerEsperienza.setSelection(myAthlete.esperienzaValue());



//        autoCompleteTextViewAllenamenti = findViewById(R.id.autoCompleteTextViewAllenamenti);
//        autoCompleteTextViewAllenamenti.setAdapter(adapterItems);
//        autoCompleteTextViewAllenamenti.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String item = (String) adapterView.getItemAtPosition(i);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        autoCompleteTextViewEsperienza = findViewById(R.id.autoCompleteTextViewEsperienza);
//        adapterItems = new ArrayAdapter<String>(this, R.layout.list_experience, itemsExperience);
//        autoCompleteTextViewEsperienza.setAdapter(adapterItems);
//        autoCompleteTextViewEsperienza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String item = (String) adapterView.getItemAtPosition(i);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


        weight = findViewById(R.id.weightValue);
        weight.setText(myAthlete.getPeso() + "");
        height = findViewById(R.id.heightValue);
        height.setText(myAthlete.getAltezza() + "");
        allenamenti = findViewById(R.id.numAllenamenti);
        allenamenti.setText(myAthlete.getAllenamentiSettimanali() + "");

        btUpdate.setOnClickListener(this);
        btReturn.setOnClickListener(this);
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

    private void onUpdate(){
        Integer peso = Integer.valueOf(weight.getText().toString());
        Integer altezza = Integer.valueOf(height.getText().toString());
        Integer numeroAllenamenti = Integer.valueOf(allenamenti.getText().toString());
        String esperienza = spinnerEsperienza.getSelectedItem().toString();

        myAthlete.setPeso(peso);
        myAthlete.setAltezza(altezza);
        myAthlete.setAllenamentiSettimanali(numeroAllenamenti);
        myAthlete.setEsperienza(esperienza);

        Task <Void> updateResult = athleteInfo.editAthleteFeatures(myAthlete, loginRegistration.getUserLogged().getUid());

        updateResult.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Caratteristiche aggiornate con successo !", Toast.LENGTH_LONG).show();
                lanciaHome();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
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