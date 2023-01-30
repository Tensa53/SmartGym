package com.example.smartgym.gestioneScheda.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartgym.R;

public class CreazioneSchedaManualeActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_exercises_2);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("Creazione Scheda Esercizi");

        tv = findViewById(R.id.tvModalita);

        tv.append(getIntent().getExtras().getString("stringa"));
    }

    public void selezionaParteDelCorpo(View v) {

        int id = v.getId();
        String partedelCorpo = null;
        Intent intent = null;

        switch (id) {
            case R.id.btbraccia: partedelCorpo = "braccia";
                break;

            case R.id.btgambe: partedelCorpo = "gambe"; break;

            case R.id.btaddome: partedelCorpo = "addome"; break;

            case R.id.btpetto: partedelCorpo = "petto"; break;

            case R.id.btschiena: partedelCorpo = "schiena"; break;

            case R.id.bttutto: partedelCorpo = "tuttoilcorpo"; break;
        }

        intent = new Intent(getApplicationContext(), SelezionaEsercizi.class);
        intent.putExtra("PARTEDELCORPO", partedelCorpo);
        startActivity(intent);

        Toast.makeText(this, "Hai selezionato: " + partedelCorpo, Toast.LENGTH_LONG).show();
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