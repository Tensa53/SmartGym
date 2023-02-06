package com.example.smartgym.gestioneScheda.application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartgym.R;
import com.example.smartgym.gestioneScheda.storage.dataAcess.EsercizioDAO;
import com.example.smartgym.gestioneScheda.storage.dataAcess.SchedaEserciziDAO;
import com.example.smartgym.gestioneScheda.storage.entity.DettaglioEsercizio;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;

import java.util.ArrayList;

public class VisualizzaSchedaEserciziActivity extends AppCompatActivity {

    String nome;

    TextView tv1;

    EsercizioDAO esercizioDAO;

    SchedaEserciziDAO schedaEserciziDAO;

    CustomAdapterEsercizi customAdapterEsercizi;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_scheda_esercizi);

        tv1 = findViewById(R.id.tv1);
        lv = findViewById(R.id.lv1);

        String nome = getIntent().getStringExtra("NOMESCHEDA");

        schedaEserciziDAO = new SchedaEserciziDAO();

        esercizioDAO = new EsercizioDAO();

        customAdapterEsercizi = new CustomAdapterEsercizi(this,R.layout.list_esercizi_item,new ArrayList<Esercizio>());

        lv.setAdapter(customAdapterEsercizi);

        tv1.setText(nome);

        populateList();
    }

    private void populateList() {
        customAdapterEsercizi.add(new Esercizio("PushUp",new DettaglioEsercizio(10,0)));
        customAdapterEsercizi.add(new Esercizio("TricepDip",new DettaglioEsercizio(10,0)));
        customAdapterEsercizi.add(new Esercizio("Squat",new DettaglioEsercizio(10,0)));
        customAdapterEsercizi.add(new Esercizio("Trazioni",new DettaglioEsercizio(10,0)));
        customAdapterEsercizi.add(new Esercizio("Crunch",new DettaglioEsercizio(10,0)));
        customAdapterEsercizi.add(new Esercizio("Jumping Jacks",new DettaglioEsercizio(10,0)));
        customAdapterEsercizi.add(new Esercizio("Mountain Climber",new DettaglioEsercizio(10,0)));
        customAdapterEsercizi.add(new Esercizio("Plank",new DettaglioEsercizio(0,30)));
        customAdapterEsercizi.add(new Esercizio("Cobra Stretch",new DettaglioEsercizio(10,0)));
        customAdapterEsercizi.add(new Esercizio("Side Hop",new DettaglioEsercizio(0,20)));
    }


}