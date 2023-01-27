package com.example.smartgym.gestioneScheda.application.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartgym.R;

public class SelezioneModalitaSchedaActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("Creazione Scheda Esercizi");

        setContentView(R.layout.activity_selezione_modalita_scheda);

        tv = findViewById(R.id.tvselezionemodalita);

        tv.setText(getIntent().getExtras().getString("stringa"));
    }

    public void selezionaModalita(View v) {
        Button b = (Button) v;

        Intent intent = null;

        int id = b.getId();

        switch (id) {
            case R.id.btmanuale:
                intent = new Intent(getApplicationContext(), CreazioneSchedaManualeActivity.class);
                break;
            case R.id.btgenerata: //TODO
                break;
            case R.id.bttrainer: //TODO
                break;
        }

        intent.putExtra("stringa", b.getText().toString());
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

}