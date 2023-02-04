package com.example.smartgym.gestioneScheda.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartgym.R;
import com.example.smartgym.gestioneScheda.storage.dataAcess.EsercizioDAO;
import com.example.smartgym.gestioneScheda.storage.entity.DettaglioEsercizio;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SelezionaEsercizi extends AppCompatActivity {

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

        esercizioDAO = new EsercizioDAO();
        esercizi = new ArrayList<>();

        String parteDelCorpo = getIntent().getStringExtra("PARTEDELCORPO");
        lv = (ListView) findViewById(R.id.eserciziLV);

        aumenta = lv.findViewById(R.id.aumentaButton);
        decrementa = lv.findViewById(R.id.decrementaButton);
        counter = lv.findViewById(R.id.tvRepsTimeValue);

        customAdapterEserciziChecked = new CustomAdapterEserciziChecked(this, R.layout.list_esercizi_item_check, new ArrayList<Esercizio>());

        lv.setAdapter(customAdapterEserciziChecked);

//        Task<QuerySnapshot> task = esercizioDAO.doRetrieveEsercizioByParteDelCorpo(parteDelCorpo);

//        recuperaEsercizio(task);

        populateList(parteDelCorpo);

    }

//    private void recuperaEsercizio(Task<QuerySnapshot> task) {
//
//        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//
//                        String nome = (String) document.get("nome");
//                        String parteDelCorpo = (String) document.get("parteDelCorpo");
//                        Esercizio esercizio = new Esercizio(nome, parteDelCorpo);
//                        saveEsercizioInLista(esercizio);
//                    }
//                } else {
//                    Toast.makeText(getApplicationContext(), "Ops", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }

//    private void saveEsercizioInLista(Esercizio esercizio) {
//
//        esercizi.add(esercizio);
//
//    }

    public void conferma(View v) {

        int tot = 0;
        ArrayList<Esercizio> checked = new ArrayList<>();

        for (Esercizio e : customAdapterEserciziChecked.esercizi) {
            if (e.isChecked) {
                checked.add(e);
                tot++;
            }
        }

        if (tot > 4 && tot < 11) {
            Intent intent = new Intent(getApplicationContext(), RiepilogoScheda.class);
            Bundle b = new Bundle();
            b.putSerializable("Esercizi", checked);
            intent.putExtras(b);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Seleziona tra i 5 e i 10 esercizi per proseguire.", Toast.LENGTH_LONG).show();
        }

    }

    private void populateList(String parteDelCorpo) {

        esercizi.add(new Esercizio("PushUp", "schiena", new DettaglioEsercizio(10, 0)));
        esercizi.add(new Esercizio("Trazioni", "schiena", new DettaglioEsercizio(10, 0)));
        esercizi.add(new Esercizio("Squat", "gambe", new DettaglioEsercizio(10, 0)));
        esercizi.add(new Esercizio("Affondi", "gambe", new DettaglioEsercizio(10, 0)));
        esercizi.add(new Esercizio("braccia1", "braccia", new DettaglioEsercizio(10, 0)));
        esercizi.add(new Esercizio("braccia2", "braccia", new DettaglioEsercizio(10, 0)));
        esercizi.add(new Esercizio("petto1", "petto", new DettaglioEsercizio(10, 0)));
        esercizi.add(new Esercizio("petto2", "petto", new DettaglioEsercizio(10, 0)));
        esercizi.add(new Esercizio("addome1", "addome", new DettaglioEsercizio(10, 0)));
        esercizi.add(new Esercizio("addome2", "addome", new DettaglioEsercizio(10, 0)));

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

        if (c1 != 0 && c1 < 60) {
            //max 60 sec
            c1++;
            e.getDettaglio().setDurata(c1);
        } else if (c2 != 0 && c2 < 20) {
            //max 20 rip
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

        if (c1 > 10) {
            //almeno 10 sec
            c1--;
            e.getDettaglio().setDurata(c1);
        } else if (c2 > 5) {
            //almeno 5 rip
            c2--;
            e.getDettaglio().setRipetizioni(c2);
        }

        customAdapterEserciziChecked.notifyDataSetChanged();
    }

}