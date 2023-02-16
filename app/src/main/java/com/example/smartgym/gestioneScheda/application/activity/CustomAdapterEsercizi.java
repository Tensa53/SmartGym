package com.example.smartgym.gestioneScheda.application.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.smartgym.R;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;

import java.util.List;

/**
 * CustomAdapterEsercizi è una sottoclasse di ArrayAdapter specializzata per la gestione degli oggetti Esercizio.
 * Questa classe viene utilizzata per visualizzare gli esercizi all'interno di una ListView.
 */
public class CustomAdapterEsercizi extends ArrayAdapter<Esercizio> {

    LayoutInflater layoutInflater;

    ImageView imgEsercizio;
    TextView tvNomeEsercizio;
    TextView tvRepsTime;
    TextView tvRepsTimeValue;

    /**
     * Costruttore per la classe CustomAdapterEsercizi.
     *
     * @param context  Il contesto corrente.
     * @param resource ID della risorsa di layout per la singola riga della ListView.
     * @param objects  La lista di oggetti Esercizio da visualizzare nella ListView.
     */
    public CustomAdapterEsercizi(@NonNull Context context, int resource, @NonNull List<Esercizio> objects) {
        super(context, resource, objects);
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * Questo metodo viene chiamato per visualizzare la vista di un singolo oggetto nella ListView.
     *
     * @param position    La posizione dell'oggetto nella ListView.
     * @param convertView La vista convertita in cui visualizzare i dati dell'oggetto.
     * @param parent      Il ViewGroup a cui la vista convertita viene aggiunta.
     * @return La vista convertita contenente i dati dell'oggetto.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = layoutInflater.inflate(R.layout.list_esercizi_item, null);

        Esercizio esercizio = getItem(position);

        imgEsercizio = convertView.findViewById(R.id.imgEsercizio);
        tvNomeEsercizio = convertView.findViewById(R.id.tvNomeEsercizio);
        tvRepsTime = convertView.findViewById(R.id.tvRepsTime);
        tvRepsTimeValue = convertView.findViewById(R.id.tvRepsTimeValue);

        imgEsercizio.setImageDrawable(getContext().getResources().getDrawable(R.drawable.logo));
        tvNomeEsercizio.setText(esercizio.getNome());

        if (esercizio.getDettaglio().getDurata() > 0) {
            tvRepsTime.setText("Durata(sec.)");
            tvRepsTimeValue.setText("00:" + esercizio.getDettaglio().getDurata());
        } else {
            tvRepsTime.setText("Ripetizioni");
            tvRepsTimeValue.setText("" + esercizio.getDettaglio().getRipetizioni());
        }

        return convertView;
    }
}
