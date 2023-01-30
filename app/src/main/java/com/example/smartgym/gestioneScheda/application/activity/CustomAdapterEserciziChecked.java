package com.example.smartgym.gestioneScheda.application.activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.smartgym.R;
import com.example.smartgym.gestioneScheda.storage.entity.DettaglioEsercizio;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterEserciziChecked extends ArrayAdapter<Esercizio> {

    LayoutInflater layoutInflater;
    ArrayList<Esercizio> esercizi;

    ImageView imgEsercizio;
    TextView tvNomeEsercizio;
    TextView tvRepsTime;
    TextView tvRepsTimeValue;
    CheckBox esercizioChecked;
    Button aumenta;
    Button decrementa;

    public CustomAdapterEserciziChecked(@NonNull Context context, int resource, @NonNull ArrayList<Esercizio> objects) {
        super(context, resource, objects);
        layoutInflater = LayoutInflater.from(context);
        this.esercizi = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = layoutInflater.inflate(R.layout.list_esercizi_item_check, null);

        Esercizio esercizio = getItem(position);

        imgEsercizio = convertView.findViewById(R.id.imgEsercizio);
        tvNomeEsercizio = convertView.findViewById(R.id.tvNomeEsercizio);
        tvRepsTime = convertView.findViewById(R.id.tvRepsTime);
        tvRepsTimeValue = convertView.findViewById(R.id.tvRepsTimeValue);
        esercizioChecked = convertView.findViewById(R.id.esercizioCheckBox);
        aumenta = convertView.findViewById(R.id.aumentaButton);
        decrementa = convertView.findViewById(R.id.decrementaButton);

        imgEsercizio.setImageDrawable(getContext().getResources().getDrawable(R.drawable.logo));
        tvNomeEsercizio.setText(esercizio.getNome());

        if (esercizio.getDettaglio().getDurata() > 0){
            tvRepsTime.setText("Durata(sec.)");
            tvRepsTimeValue.setText("00:"+esercizio.getDettaglio().getDurata());
        } else {
            tvRepsTime.setText("Ripetizioni");
            tvRepsTimeValue.setText(""+esercizio.getDettaglio().getRipetizioni());

        }

        imgEsercizio.setTag(position);
        tvNomeEsercizio.setTag(position);
        tvRepsTime.setTag(position);
        esercizioChecked.setTag(position);
        aumenta.setTag(position);
        decrementa.setTag(position);

        esercizioChecked.setChecked(esercizio.isChecked());

        esercizioChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPos = (int) v.getTag();
                if (!esercizi.get(currentPos).isChecked()) {
                    esercizi.get(currentPos).setIschecked(true);
                }
                else {
                    esercizi.get(currentPos).isChecked();
                    esercizi.get(currentPos).setIschecked(false);
                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

}