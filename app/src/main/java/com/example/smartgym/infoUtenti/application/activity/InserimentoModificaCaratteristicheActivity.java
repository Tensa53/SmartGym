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

/**
 * Questa classe rappresenta l'activity per l'inserimento e/o la modifica delle caratteristiche dell'atleta.
 * In questa activity sarà visbile un form dove inserire e/o modificare le caratteristiche e due pulsanti
 * che rispettivamente permetteranno di salvare le modifiche o annullarle
 */
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

    /**
     * Metodo di callback chiamato quando l'Activity viene creata.
     * In esso sono richiamati i relativi metodi per istanziare i widget e i campi del form che
     * sono riempiti attraverso l'atleta recuperato dall'intent. Inoltre sono settati i listener
     * e gli adapter per i pulsanti e per il dropdown menu
     *
     * @param savedInstanceState oggetto Bundle contenente lo stato dell'Activity in caso di riavvio
     */
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

    /**
     * Questo metodo viene chiamato da OnCreate() per settare i campi del form dopo aver recuperato
     * l'atleta dall'intent
     */
    private void setFields() {
        spinnerEsperienza.setSelection(myAthlete.esperienzaValue());

        etPeso.setText("" + myAthlete.getPeso());

        etAltezza.setText("" + myAthlete.getAltezza());

        etAllenamenti.setText("" + myAthlete.getAllenamentiSettimanali());

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
            case R.id.btUpdate: onUpdate();
            break;
            case R.id.btReturn: returnProfile();
            break;
        }
    }

    /**
     * Metodo che viene chiamato per effettuare il binding dei widget del layout
     * con le rispettive istanze della classe a cui appartengono
     */
    private void widgetBinding() {
        btUpdate = findViewById(R.id.btUpdate);
        btReturn = findViewById(R.id.btReturn);
        etPeso = findViewById(R.id.etPeso);
        etAltezza = findViewById(R.id.etAltezza);
        etAllenamenti = findViewById(R.id.etAllenamenti);
        spinnerEsperienza = findViewById(R.id.spinnerEsperienza);
    }

    /**
     * Questo metodo viene richiamato da onClick() per gestire le operazioni relative al salvataggio
     * delle informazioni relative all'inserimento e/o modifica caratteristiche
     */
    private void onUpdate(){
        Integer peso = Integer.valueOf(etPeso.getText().toString());
        Integer altezza = Integer.valueOf(etAltezza.getText().toString());
        Integer numeroAllenamenti = Integer.valueOf(etAllenamenti.getText().toString());
        String esperienza = spinnerEsperienza.getSelectedItem().toString();

        if (verificaCaratteristiche(peso, altezza, numeroAllenamenti)) {
            updateCaratteristicheAtleta(peso, altezza, numeroAllenamenti, esperienza);
        }
    }

    /**
     * Questo metodo viene richiamato da onUpdate() per salvare le nuove caratteristiche nel db
     * richiamando il task nella relativa classe DAO
     *
     * @param peso, il peso dell'atleta espresso in kg e memorizzato in una variabile Integer
     * @param altezza, l'altezza dell'atleta espressa in cm e memorizzata in una variabile Integer
     * @param numeroAllenamenti, il numero di allenamenti settimanali che l'atleta vuole svolegere
     * @param esperienza, lo stadio di esperienza dell'atleta
     */
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

    /**
     * Questo metodo viene richiamato da updateCaratterisiticheAtleta() per mostrare un messaggio di
     * successo quando le caratteristiche sono state correttamente memorizzate
     */
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

    /**
     * Questo metodo viene richiamato da onUpdate() per verificare i valori inseriti nei campi
     * del form. Fa uso dei metodi della classe FormUtils per validare i dati. Gestisce l'eccezione
     * AthelteFeaturesFieldException
     *
     * @param peso, il peso dell'atleta espresso in kg e memorizzato in una variabile Integer
     * @param altezza, l'altezza dell'atleta espressa in cm e memorizzata in una variabile Integer
     * @param numeroAllenamenti, il numero di allenamenti settimanali che l'atleta vuole svolegere
     * @return boolean, un valore true/false a seconda dell'avvenuta validazione dei valori dei campi
     */
    private boolean verificaCaratteristiche(Integer peso, Integer altezza, Integer numeroAllenamenti) {
        try {
            formUtils.controllaCaratteristicheAtleta(peso, altezza, numeroAllenamenti);
        } catch (AthleteFeaturesFieldException e) {
            showError(e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Questo metodo viene richiamato da verificaCaratteristiche() per mostrare un messaggio d'errore
     * sul campo che non ha superato la validazione
     *
     * @param error, rappresenta il messaggio di errore da visualizzare
     */
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

    /**
     * Questo metodo viene richiamato da mostraAvviso() per riportare l'atleta alla home dopo aver
     * inserito correttamente le informazioni
     */
    private void lanciaHome() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    /**
     * Questo metodo viene richiamato da onClick() per gestire le operazioni relative al pulsante
     * che annulla l'operazione e riporta alla vista precedente
     */
    private void returnProfile(){
        super.onBackPressed();
    }
}