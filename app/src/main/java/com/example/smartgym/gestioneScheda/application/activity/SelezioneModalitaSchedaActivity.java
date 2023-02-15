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

/**
 * Activity che consente di selezionare la modalità per la creazione di una scheda di esercizi.
 */
public class SelezioneModalitaSchedaActivity extends AppCompatActivity {

    /**
     * Metodo chiamato quando l'activity viene creata. Inizializza l'action bar, il titolo e il layout della vista.
     *
     * @param savedInstanceState Oggetto che contiene i dati dello stato dell'activity salvati in precedenza.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("Creazione Scheda Esercizi");

        setContentView(R.layout.activity_selezione_modalita_scheda);
    }

    /**
     * Metodo richiamato quando viene selezionato uno dei pulsanti per la scelta della modalità. In base al pulsante
     * selezionato, avvia un'activity diversa per la scelta della parte del corpo, della scheda generata o del trainer.
     *
     * @param v Vista corrente della activity.
     */
    public void selezionaModalita(View v) {
        Button b = (Button) v;

        Intent intent = null;

        int id = b.getId();

        switch (id) {
            case R.id.btmanuale:
                intent = new Intent(getApplicationContext(), ScegliParteDelCorpoActivity.class);
                break;
            case R.id.btgenerata: //TODO
                break;
            case R.id.bttrainer: //TODO
                break;
        }

        startActivity(intent);
    }

    /**
     * Metodo richiamato quando l'utente preme il pulsante indietro sul dispositivo.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * Metodo richiamato quando un elemento dell'action bar viene selezionato. Richiama il metodo {@link #onBackPressed()}
     * per tornare all'activity precedente.
     *
     * @param item Elemento dell'action bar selezionato.
     * @return true.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

}