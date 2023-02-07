package com.example.smartgym.infoUtenti.application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartgym.R;
import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;
import com.example.smartgym.infoUtenti.storage.entity.Atleta;

public class ModificaCaratteristicheActivity extends AppCompatActivity implements View.OnClickListener {

    Button btUpdate, btReturn;
    TextView weight, height;
    Atleta myAthlete;
    LoginRegistration loginRegistration;
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

        btUpdate = findViewById(R.id.btUpdate);
        btReturn = findViewById(R.id.btReturn);

        autoCompleteTextViewAllenamenti = findViewById(R.id.autoCompleteTextViewAllenamenti);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_num_allenamenti, itemsAllenamenti);
        autoCompleteTextViewAllenamenti.setAdapter(adapterItems);
        autoCompleteTextViewAllenamenti.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        autoCompleteTextViewEsperienza = findViewById(R.id.autoCompleteTextViewEsperienza);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_experience, itemsExperience);
        autoCompleteTextViewEsperienza.setAdapter(adapterItems);
        autoCompleteTextViewEsperienza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        weight = findViewById(R.id.weightValue);
        weight.setText(myAthlete.getPeso() + "");
        height = findViewById(R.id.heightValue);
        height.setText(myAthlete.getAltezza() + "");

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