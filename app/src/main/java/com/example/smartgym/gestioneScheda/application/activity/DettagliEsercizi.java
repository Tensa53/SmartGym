package com.example.smartgym.gestioneScheda.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartgym.R;
import com.example.smartgym.gestioneScheda.storage.dataAcess.EsercizioDAO;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DettagliEsercizi extends AppCompatActivity {

    TextView tvNomeEsercizio, tvDescrizione, tvDifficolta, tvParteDelCorpo, tvTipologia;
    EsercizioDAO esercizioDAO;
    String descrizione,tipologia,difficolta,parteDelCorpo,nome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli_esercizi);

//        esercizioDAO = new EsercizioDAO();

        Intent i = getIntent();
        nome = i.getStringExtra("ESERCIZIO");

        tvNomeEsercizio = findViewById(R.id.tvNomeEsercizio);
        tvDescrizione = findViewById(R.id.tvDescrizione);
        tvDifficolta = findViewById(R.id.tvDifficolta);
        tvParteDelCorpo = findViewById(R.id.tvParteDelCorpo);
        tvTipologia = findViewById(R.id.tvTipologia);

        tvNomeEsercizio.setText("Nome esercizio: " + nome);
        tvDescrizione.setText("Descrizione: " + descrizione);
        tvDifficolta.setText("Difficoltà: " + difficolta);
        tvParteDelCorpo.setText("Parte del corpo allenata: " + parteDelCorpo);
        tvTipologia.setText("Tipologia: " + tipologia);

        //tentativi (falliti) di recuperare esercizi dal db

//        Task<QuerySnapshot> task = esercizioDAO.doRetrieveEsercizioById2(id);
//        recuperaEsercizio(task);

    }

//        private void recuperaEsercizio(Task<QuerySnapshot> task) {
//
//        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//
//                        nome = (String) document.get("nome");
//                        parteDelCorpo = (String) document.get("parteDelCorpo");
//                        descrizione = (String) document.get("descrizione");
//                        difficolta = (String) document.get("difficolta");
//                        tipologia = (String) document.get("tipologia");
//                        Esercizio esercizio = new Esercizio(nome, descrizione, difficolta, parteDelCorpo, tipologia);
//                        visualizzaTv(esercizio);
//                    }
//                } else {
//                    Toast.makeText(getApplicationContext(), "Ops", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }

//        private void recuperaEsercizio(Task<DocumentSnapshot> task) {
//
//        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//
//                        nome = (String) document.get("nome");
//                        parteDelCorpo = (String) document.get("parteDelCorpo");
//                        descrizione = (String) document.get("descrizione");
//                        difficolta = (String) document.get("difficolta");
//                        tipologia = (String) document.get("tipologia");
//                    }
//                } else {
//                    Toast.makeText(getApplicationContext(), "Ops", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }

    public void indietro(View v) {
        super.onBackPressed();
    }

}
