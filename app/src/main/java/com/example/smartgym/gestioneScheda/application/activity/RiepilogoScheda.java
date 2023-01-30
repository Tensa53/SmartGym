package com.example.smartgym.gestioneScheda.application.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartgym.R;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;

import java.util.ArrayList;

public class RiepilogoScheda extends AppCompatActivity {

    private ArrayList<Esercizio> esercizi = new ArrayList<Esercizio>();
    ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riepilogo_scheda);

        Bundle bundle =  getIntent().getExtras();

        esercizi = (ArrayList<Esercizio>)bundle.getSerializable("Esercizi");

        lv = (ListView) findViewById(R.id.eserciziLV);

        CustomAdapterEsercizi customAdapterEsercizi = new CustomAdapterEsercizi(this, R.layout.list_esercizi_item, esercizi);

        lv.setAdapter(customAdapterEsercizi);

    }

    public void conferma(View v){

    }

    public void indietro(View v){
        super.onBackPressed();
    }

}
