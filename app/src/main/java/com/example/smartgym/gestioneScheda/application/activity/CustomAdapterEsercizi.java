package com.example.smartgym.gestioneScheda.application.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

import org.w3c.dom.Text;

import java.util.List;

public class CustomAdapterEsercizi extends ArrayAdapter<Esercizio> {

    LayoutInflater layoutInflater;

    ImageView imgEsercizio;
    TextView tvNomeEsercizio;
    TextView tvRepsTime;
    TextView tvRepsTimeValue;

    public CustomAdapterEsercizi(@NonNull Context context, int resource, @NonNull List<Esercizio> objects) {
        super(context, resource, objects);
        layoutInflater = LayoutInflater.from(context);
    }

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

        if (esercizio.getDettaglio().getDurata() > 0){
            tvRepsTime.setText("Durata(sec.)");
            tvRepsTimeValue.setText("00:"+esercizio.getDettaglio().getDurata());
        } else {
            tvRepsTime.setText("Ripetizioni");
            tvRepsTimeValue.setText(""+esercizio.getDettaglio().getRipetizioni());
        }

        return convertView;
    }
}
