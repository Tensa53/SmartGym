package com.example.smartgym.gestioneScheda.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartgym.R;


/**
 * Questa classe rappresenta l'Activity che consente all'utente di selezionare la parte del corpo per cui creare una scheda di esercizi.
 * L'Activity viene avviata tramite l'Intent di un'altra Activity e visualizza un layout con diversi bottoni, ognuno dei quali rappresenta una parte del corpo.
 * Quando l'utente seleziona una parte del corpo, viene creato un nuovo Intent per avviare l'Activity SelezionaEserciziActivity, al quale viene passata la stringa che rappresenta la parte del corpo selezionata come parametro.
 * Inoltre, l'Activity mostra una ActionBar con il titolo "Creazione Scheda Esercizi" e un pulsante per tornare indietro.
 */
public class ScegliParteDelCorpoActivity extends AppCompatActivity {

    /**
     * Metodo chiamato quando l'Activity viene creata. Inizializza la ActionBar e imposta il layout del contenuto dell'Activity.
     *
     * @param savedInstanceState l'istanza precedente salvata dell'Activity, se presente.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_exercises_2);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("Creazione Scheda Esercizi");
    }

    /**
     * Metodo chiamato quando l'utente seleziona una parte del corpo. Viene creato un Intent per avviare l'Activity SelezionaEserciziActivity, al quale viene passata la stringa che rappresenta la parte del corpo selezionata come parametro.
     *
     * @param v la View corrispondente al bottone selezionato dall'utente.
     */
    public void selezionaParteDelCorpo(View v) {

        int id = v.getId();
        String partedelCorpo = null;
        Intent intent = null;

        switch (id) {
            case R.id.btbraccia:
                partedelCorpo = "braccia";
                break;

            case R.id.btgambe:
                partedelCorpo = "gambe";
                break;

            case R.id.btaddome:
                partedelCorpo = "addome";
                break;

            case R.id.btpetto:
                partedelCorpo = "petto";
                break;

            case R.id.btschiena:
                partedelCorpo = "schiena";
                break;

            case R.id.bttutto:
                partedelCorpo = "tuttoilcorpo";
                break;
        }

        intent = new Intent(getApplicationContext(), SelezionaEserciziActivity.class);
        intent.putExtra("PARTEDELCORPO", partedelCorpo);
        startActivity(intent);
    }

    /**
     * Metodo chiamato quando l'utente preme il pulsante "indietro" sulla ActionBar. Richiama il metodo onBackPressed della superclasse per chiudere l'Activity corrente.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * Metodo chiamato quando l'utente seleziona un elemento della ActionBar. Richiama il metodo onBackPressed per chiudere l'Activity corrente e tornare indietro.
     *
     * @param item il MenuItem corrispondente all'elemento selezionato dall'utente.
     * @return true per indicare che l'evento è stato gestito correttamente.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}