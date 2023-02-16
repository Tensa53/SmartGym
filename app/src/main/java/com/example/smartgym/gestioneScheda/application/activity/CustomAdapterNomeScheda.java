package com.example.smartgym.gestioneScheda.application.activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.smartgym.gestioneScheda.storage.entity.SchedaEsercizi;

import java.util.List;

/**
 * Adapter personalizzato per la visualizzazione del nome delle schede di esercizi in una ListView.
 * Estende la classe ArrayAdapter.
 */

public class CustomAdapterNomeScheda extends ArrayAdapter<SchedaEsercizi> {

    LayoutInflater layoutInflater;
    TextView tv;


    /**
     * Costruttore della classe.
     *
     * @param context Il contesto dell'applicazione.
     * @param resource L'id della risorsa layout per la singola riga della ListView.
     * @param objects La lista degli oggetti SchedaEsercizi da visualizzare.
     */
    public CustomAdapterNomeScheda(@NonNull Context context, int resource, @NonNull List<SchedaEsercizi> objects) {
        super(context, resource, objects);
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * Metodo chiamato per la visualizzazione di ogni elemento della ListView.
     *
     * @param position La posizione dell'elemento nella ListView.
     * @param convertView La View della singola riga della ListView.
     * @param parent Il ViewGroup contenitore della ListView.
     * @return La View aggiornata della singola riga della ListView.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            Log.d("DEBUG","Inflating view");
            convertView = layoutInflater.inflate(com.example.smartgym.R.layout.list_nome_scheda_item,null);
        }

        SchedaEsercizi schedaEsercizi = getItem(position);

        tv = convertView.findViewById(com.example.smartgym.R.id.textViewList);

        tv.setText(schedaEsercizi.getNome());

        return  convertView;
    }
}
