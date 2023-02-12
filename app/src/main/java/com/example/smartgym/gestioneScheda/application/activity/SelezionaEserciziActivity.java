package com.example.smartgym.gestioneScheda.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartgym.R;
import com.example.smartgym.gestioneScheda.application.exception.ExcersisesDurationExceededException;
import com.example.smartgym.gestioneScheda.application.exception.ExercisesRepsExceededException;
import com.example.smartgym.gestioneScheda.application.exception.NumberExercsisesExceededException;
import com.example.smartgym.gestioneScheda.storage.dataAcess.EsercizioDAO;
import com.example.smartgym.gestioneScheda.storage.entity.DettaglioEsercizio;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;
import com.example.smartgym.utils.FormUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SelezionaEserciziActivity extends AppCompatActivity {

    public Button aumenta;
    public Button decrementa;
    public TextView counter;
    CustomAdapterEserciziChecked customAdapterEserciziChecked;
    ListView lv;
    EsercizioDAO esercizioDAO;
    ArrayList<Esercizio> esercizi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleziona_esercizi);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Creazione Scheda Esercizi");

        esercizioDAO = new EsercizioDAO();
        esercizi = new ArrayList<>();

        String parteDelCorpo = getIntent().getStringExtra("PARTEDELCORPO");
        lv = (ListView) findViewById(R.id.eserciziLV);

        aumenta = lv.findViewById(R.id.aumentaButton);
        decrementa = lv.findViewById(R.id.decrementaButton);
        counter = lv.findViewById(R.id.tvRepsTimeValue);

        customAdapterEserciziChecked = new CustomAdapterEserciziChecked(this, R.layout.list_esercizi_item_check, new ArrayList<Esercizio>());

        lv.setAdapter(customAdapterEserciziChecked);

//        populateList(parteDelCorpo);

        if (parteDelCorpo.equalsIgnoreCase("tuttoilcorpo")){
            Task<QuerySnapshot> task = esercizioDAO.doRetrieveAllEsercizi();

            recuperaEsercizio(task);
        } else {
            Task<QuerySnapshot> task = esercizioDAO.doRetrieveEsercizioByParteDelCorpo(parteDelCorpo);

            recuperaEsercizio(task);
        }
    }

    private void recuperaEsercizio(Task<QuerySnapshot> task) {

        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String nome = (String) document.get("nome");
                        String parteDelCorpo = (String) document.get("parteDelCorpo");
                        String descrizione = (String) document.get("descrizione");
                        String difficolta = (String) document.get("difficolta");
                        String tipologia = (String) document.get("tipologia");
                        String esecuzione = (String) document.get("esecuzione");
                        Esercizio esercizio = new Esercizio(nome,parteDelCorpo,descrizione,difficolta,tipologia,esecuzione);

                        if (esercizio.getEsecuzione().equalsIgnoreCase("serie"))
                            esercizio.setDettaglio(new DettaglioEsercizio(0, -1));
                        else if (esercizio.getEsecuzione().equalsIgnoreCase("tempo"))
                            esercizio.setDettaglio(new DettaglioEsercizio(-1, 0));

                        saveEsercizioInLista(esercizio);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Ops", Toast.LENGTH_SHORT).show();
                }
            }
        });

        customAdapterEserciziChecked.notifyDataSetChanged();

    }

    private void saveEsercizioInLista(Esercizio esercizio) {
        esercizi.add(esercizio);
        customAdapterEserciziChecked.add(esercizio);
    }

    public void conferma(View v) {
        ArrayList<Esercizio> checked = new ArrayList<>();

        for (Esercizio e : customAdapterEserciziChecked.esercizi) {
            if (e.isChecked)
                checked.add(e);
        }

        if(verificaScheda(checked)){
            Intent intent = new Intent(getApplicationContext(), RiepilogoSchedaActivity.class);
            Bundle b = new Bundle();
            b.putSerializable("Esercizi", checked);
            intent.putExtras(b);
            startActivity(intent);
        }

    }

    private boolean verificaScheda(ArrayList<Esercizio> checked) {
        FormUtils formUtils = new FormUtils();

        try {
            formUtils.controllaListaEsercizi(checked);
        } catch (NumberExercsisesExceededException | ExercisesRepsExceededException | ExcersisesDurationExceededException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void populateList(String parteDelCorpo) {
        esercizi.add(new Esercizio("PushUp", "schiena", new DettaglioEsercizio(10, -1)));
        esercizi.add(new Esercizio("Trazioni", "schiena", new DettaglioEsercizio(10, -1)));
        esercizi.add(new Esercizio("Squat", "gambe", new DettaglioEsercizio(10, -1)));
        esercizi.add(new Esercizio("Affondi", "gambe", new DettaglioEsercizio(10, -1)));
        esercizi.add(new Esercizio("braccia1", "braccia", new DettaglioEsercizio(10, -1)));
        esercizi.add(new Esercizio("braccia2", "braccia", new DettaglioEsercizio(10, -1)));
        esercizi.add(new Esercizio("petto1", "petto", new DettaglioEsercizio(10, -1)));
        esercizi.add(new Esercizio("petto2", "petto", new DettaglioEsercizio(10, -1)));
        esercizi.add(new Esercizio("addome1", "addome", new DettaglioEsercizio(10, -1)));
        esercizi.add(new Esercizio("addome2", "addome", new DettaglioEsercizio(10, -1)));

        for (Esercizio e : esercizi) {
            if (e.getParteDelCorpo().equalsIgnoreCase(parteDelCorpo)) {
                customAdapterEserciziChecked.add(e);
            } else if (parteDelCorpo.equalsIgnoreCase("tuttoilcorpo")) {
                customAdapterEserciziChecked.add(e);
            }
        }

    }

    public void aumenta(View v) {
        int position = Integer.parseInt(v.getTag().toString());
        Esercizio e = customAdapterEserciziChecked.getItem(position);
        int c1 = e.getDettaglio().getDurata();
        int c2 = e.getDettaglio().getRipetizioni();

        if (c1 >= 0) {
            c1+=5;
            e.getDettaglio().setDurata(c1);
        } else if (c2 >= 0) {
            c2++;
            e.getDettaglio().setRipetizioni(c2);
        }

        customAdapterEserciziChecked.notifyDataSetChanged();

    }

    public void decrementa(View v) {
        int position = Integer.parseInt(v.getTag().toString());
        Esercizio e = customAdapterEserciziChecked.getItem(position);
        int c1 = e.getDettaglio().getDurata();
        int c2 = e.getDettaglio().getRipetizioni();

        if (c1 > 0) {
            c1-=5;
            e.getDettaglio().setDurata(c1);
        } else if (c2 > 0) {
            c2--;
            e.getDettaglio().setRipetizioni(c2);
        }

        customAdapterEserciziChecked.notifyDataSetChanged();
    }

}