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

import com.example.smartgym.gestioneScheda.storage.entity.RealScheda;
import com.example.smartgym.gestioneScheda.storage.entity.SchedaEsercizi;

import java.util.List;

public class CustomAdapterNomeScheda extends ArrayAdapter<SchedaEsercizi> {

    LayoutInflater layoutInflater;
    TextView tv;

    public CustomAdapterNomeScheda(@NonNull Context context, int resource, @NonNull List<SchedaEsercizi> objects) {
        super(context, resource, objects);
        layoutInflater = LayoutInflater.from(context);
    }

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
