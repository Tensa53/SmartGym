package com.example.smartgym.infoUtenti.application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartgym.R;

public class ModificaCaratteristicheActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_caratteristiche);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
    }
        /*
        switch (id){
            case R.id.btUpdate: onUpdate();
        }
        */
}