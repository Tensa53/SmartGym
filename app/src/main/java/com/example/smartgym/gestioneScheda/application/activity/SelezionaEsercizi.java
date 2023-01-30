package com.example.smartgym.gestioneScheda.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartgym.R;
import com.example.smartgym.gestioneScheda.storage.entity.DettaglioEsercizio;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SelezionaEsercizi extends AppCompatActivity {

    public Button aumenta;
    public Button decrementa;
    public TextView counter;
    CustomAdapterEserciziChecked customAdapterEserciziChecked;
    ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleziona_esercizi);

        String parteDelCorpo = getIntent().getStringExtra("PARTEDELCORPO");
        lv = (ListView) findViewById(R.id.eserciziLV);

        aumenta = lv.findViewById(R.id.aumentaButton);
        decrementa = lv.findViewById(R.id.decrementaButton);
        counter = lv.findViewById(R.id.tvRepsTimeValue);

        customAdapterEserciziChecked = new CustomAdapterEserciziChecked(this, R.layout.list_esercizi_item_check, new ArrayList<Esercizio>());

        lv.setAdapter(customAdapterEserciziChecked);

        populateList(parteDelCorpo);

    }

    public void conferma(View v) {

        int tot = 0;
        ArrayList<Esercizio> checked = new ArrayList<>();

        for (Esercizio e: customAdapterEserciziChecked.esercizi) {
            if (e.isChecked){
                checked.add(e);
                tot++;
            }
        }

        if( tot > 4 && tot < 11) {
            Intent intent = new Intent(getApplicationContext(), RiepilogoScheda.class);
            Bundle b = new Bundle();
            b.putSerializable("Esercizi", checked);
            intent.putExtras(b);
            startActivity(intent);
        } else {
            Toast.makeText(this,"Seleziona tra i 5 e i 10 esercizi per proseguire.",Toast.LENGTH_LONG).show();
        }

    }

    private void populateList(String parteDelCorpo) {

        ArrayList<Esercizio> esercizi = new ArrayList<>();

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

        if (c1 != 0) {
            //aumentiamo la durata
            c1++;
            e.getDettaglio().setDurata(c1);
        } else if (c2 != 0) {
            //aumentiamo le ripetizioni
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

        if (c1 != 0) {
            //durata
            c1--;
            e.getDettaglio().setDurata(c1);
        } else if (c2 != 0) {
            //ripetizioni
            c2--;
            e.getDettaglio().setRipetizioni(c2);
        }

        customAdapterEserciziChecked.notifyDataSetChanged();
    }

}