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
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;
import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;
import com.example.smartgym.start.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Questa classe rappresenta l'Activity per il riepilogo della scheda di allenamento.
 * In essa è possibile visualizzare gli esercizi scelti, inserire un nome per la scheda e
 * confermare il salvataggio della scheda.
 */
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

    /**
     * Metodo di callback chiamato quando l'Activity viene creata.
     * In esso vengono inizializzati i componenti grafici e gli oggetti necessari per la gestione
     * della scheda.
     *
     * @param savedInstanceState oggetto Bundle contenente lo stato dell'Activity in caso di riavvio
     */
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

        customAdapterEsercizi = new CustomAdapterEsercizi(this, R.layout.list_esercizi_item, esercizi);

        lv.setAdapter(customAdapterEsercizi);

    }

    /**
     * Metodo richiamato dal pulsante "Conferma" per salvare la scheda.
     * In questo metodo viene costruita la scheda con i dati inseriti dall'utente e gli esercizi scelti,
     * quindi viene salvata nel database.
     *
     * @param v oggetto View che ha generato l'evento del click sul pulsante "Conferma"
     */
    public void conferma(View v) {

        nomeScheda = etNomeScheda.getText().toString();

        if (!nomeScheda.isEmpty()) {
            schedaEs.put("nome", nomeScheda);
            schedaEs.put("pubblica", false);
            schedaEs.put("modalita", "manuale");
            schedaEs.put("ricevente", "/atleti/" + loginRegistration.getUserLogged().getUid());
            schedaEs.put("inUso", false);

            for (int i = 0; i < lv.getChildCount(); i++) {
                Esercizio esercizio = (Esercizio) lv.getItemAtPosition(i);

                dettagli.put("durata", esercizio.getDettaglio().getDurata());
                dettagli.put("ripetizioni", esercizio.getDettaglio().getRipetizioni());
                dettagli.put("esercizio", "/esercizi/" + esercizio.getId());

                idDettagli.add(schedaLogic.saveDettaglioEsercizio(dettagli));

                dettagli.clear();
            }

            schedaEs.put("esercizi_scelti", idDettagli);

            Task<Void> saveResult = schedaLogic.saveScheda(schedaEs);

            saveResult.addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful())
                        mostraMessaggio();
                }
            });
        } else
            etNomeScheda.setError("Inserisci un nome scheda");

    }

    /**
     * Mostra un dialog con un messaggio di successo dopo il salvataggio della scheda. Al click del tasto "OK",
     * lancia l'activity principale.
     */
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

    /**
     * Lancia l'activity principale e termina l'activity corrente.
     */
    private void lanciaHome() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    /**
     * Metodo richiamato quando viene premuto il pulsante "Indietro". Chiude l'activity corrente e torna alla
     * activity precedente.
     *
     * @param v la view del pulsante "Indietro"
     */
    public void indietro(View v) {
        super.onBackPressed();
    }

}

