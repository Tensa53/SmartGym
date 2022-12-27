package com.example.smartgym.application;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.smartgym.R;

public class ExercisesFragment extends Fragment implements View.OnClickListener {

    Button bt1;

    public ExercisesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercises, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bt1 = getView().findViewById(R.id.bt1);

        bt1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.bt1: lanciaActivity1(); break;
        }
    }

    private void lanciaActivity1() {
        Intent i = new Intent(getContext(), ExercisesActivity1.class);

        i.putExtra("stringa", "Seleziona la modalita di creazione della scheda: ");

        startActivity(i);
    }
}