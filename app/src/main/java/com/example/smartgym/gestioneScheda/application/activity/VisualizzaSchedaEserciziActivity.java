package com.example.smartgym.gestioneScheda.application.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartgym.R;
import com.example.smartgym.gestioneScheda.storage.dataAcess.EsercizioDAO;
import com.example.smartgym.gestioneScheda.storage.dataAcess.SchedaEserciziDAO;
import com.example.smartgym.gestioneScheda.storage.entity.DettaglioEsercizio;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;

import java.util.ArrayList;

public class VisualizzaSchedaEserciziActivity extends AppCompatActivity implements View.OnClickListener {

    String nome;

    TextView tv1;

    EsercizioDAO esercizioDAO;

    SchedaEserciziDAO schedaEserciziDAO;

    CustomAdapterEsercizi customAdapterEsercizi;

    ListView lv;

    Button btFissa,btModifica,btCancella;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_scheda_esercizi);

        widgetBinding();

        String nome = getIntent().getStringExtra("NOMESCHEDA");

        tv1.setText(nome);

        btFissa.setOnClickListener(this);
        btModifica.setOnClickListener(this);
        btCancella.setOnClickListener(this);

        schedaEserciziDAO = new SchedaEserciziDAO();

        esercizioDAO = new EsercizioDAO();

        customAdapterEsercizi = new CustomAdapterEsercizi(this,R.layout.list_esercizi_item,new ArrayList<Esercizio>());

        lv.setAdapter(customAdapterEsercizi);

        populateList();
    }

    private void widgetBinding() {
        tv1 = findViewById(R.id.tv1);
        lv = findViewById(R.id.lv1);
        btFissa = findViewById(R.id.btFissaScheda);
        btModifica = findViewById(R.id.btModificaScheda);
        btCancella = findViewById(R.id.btCancellaScheda);
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


    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btFissaScheda: Toast.makeText(getApplicationContext(), "La scheda viene fissata nella home e considerata come in uso", Toast.LENGTH_SHORT).show();
            break;
            case R.id.btModificaScheda: //TODO
            break;
            case R.id.btCancellaScheda: onCancella();
        }
    }

    private void onCancella() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE:
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Sei sicuro di voler cancellare la scheda ?")
                .setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
}