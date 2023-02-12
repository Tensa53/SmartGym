package com.example.smartgym.gestioneScheda.application.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartgym.R;
import com.example.smartgym.gestioneScheda.application.logic.SchedaLogic;
import com.example.smartgym.gestioneScheda.storage.dataAcess.EsercizioDAO;
import com.example.smartgym.gestioneScheda.storage.dataAcess.SchedaEserciziDAO;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;
import com.example.smartgym.gestioneScheda.storage.entity.RealScheda;
import com.example.smartgym.infoUtenti.application.logic.AthleteInfo;
import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;
import com.example.smartgym.start.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RiepilogoSchedaActivity extends AppCompatActivity {

    private ArrayList<Esercizio> esercizi = new ArrayList<Esercizio>();
    ListView lv;
    EditText etNomeScheda;
    SchedaLogic schedaLogic;
    CustomAdapterEsercizi customAdapterEsercizi;

    String nomeScheda = "";

    Map<String, Object> dettagli = new HashMap<>();

    Map<String, Object> schedaEs = new HashMap<>();

    ArrayList<String> idDettagli = new ArrayList<>();

    LoginRegistration loginRegistration;
    AthleteInfo athleteInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riepilogo_scheda);

        Bundle bundle = getIntent().getExtras();

        loginRegistration = new LoginRegistration();

        esercizi = (ArrayList<Esercizio>) bundle.getSerializable("Esercizi");

        lv = (ListView) findViewById(R.id.eserciziLV);
        etNomeScheda = findViewById(R.id.etNomeScheda);

        schedaLogic = new SchedaLogic();

//        athleteInfo = new AthleteInfo();
//        loginRegistration = new LoginRegistration();
//
//        FirebaseUser user = loginRegistration.isUserLogged();
//
//        if (user != null)
//            recuperaAtleta(user.getEmail());

        //SchedaEsercizi scheda = new SchedaEsercizi(uid???,user.getDisplayName(),esercizi);

        customAdapterEsercizi = new CustomAdapterEsercizi(this, R.layout.list_esercizi_item, esercizi);

        lv.setAdapter(customAdapterEsercizi);

    }

    public void conferma(View v) {

        nomeScheda = etNomeScheda.getText().toString();

        if (!nomeScheda.isEmpty()) {
            EsercizioDAO esercizioDAO = new EsercizioDAO();

            schedaEs.put("nome",nomeScheda);
            schedaEs.put("pubblica", false);
            schedaEs.put("modalita", "manuale");
            schedaEs.put("ricevente", "/atleti/"+loginRegistration.getUserLogged().getUid());
            schedaEs.put("inUso", false);

            for (int i = 0; i < lv.getChildCount(); i++) {
                Esercizio esercizio = (Esercizio) lv.getItemAtPosition(i);

                dettagli.put("durata", esercizio.getDettaglio().getDurata());
                dettagli.put("ripetizioni", esercizio.getDettaglio().getRipetizioni());
                dettagli.put("esercizio", "/esercizi/"+esercizio.getId());

                idDettagli.add(esercizioDAO.doSaveDettaglioEsercizio(dettagli));

                dettagli.clear();
            }

            schedaEs.put("esercizi_scelti",idDettagli);

            SchedaEserciziDAO schedaEserciziDAO = new SchedaEserciziDAO();

            Task<Void> saveResult = schedaEserciziDAO.doSaveScheda(schedaEs);

            saveResult.addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful())
                        mostraMessaggio();
                }
            });
        } else
            etNomeScheda.setError("Inserisci un nome scheda");

//        schedaLogic.saveScheda(scheda);

//        Intent intent = new Intent(getApplicationContext(), SchedaSuccess.class);
//        startActivity(intent);

    }

    private void mostraMessaggio() {
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

        builder.setMessage("Scheda salvata con successo !")
                .setPositiveButton("OK", dialogClickListener).show();
    }

    private void lanciaHome() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void indietro(View v) {
        super.onBackPressed();
    }

//    private void recuperaAtleta(String email) {
//        Task<DocumentSnapshot> task = athleteInfo.getAthletebyEmail(email);
//
//        task.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                Atleta atleta = documentSnapshot.toObject(Atleta.class);
//                Log.d("DEBUG",atleta.getNome());
//            }
//        });
//    }

}

