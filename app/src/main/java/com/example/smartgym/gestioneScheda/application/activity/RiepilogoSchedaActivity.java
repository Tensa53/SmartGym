package com.example.smartgym.gestioneScheda.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartgym.R;
import com.example.smartgym.gestioneScheda.application.logic.SchedaLogic;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;
import com.example.smartgym.gestioneScheda.storage.entity.SchedaEsercizi;
import com.example.smartgym.infoUtenti.application.logic.AthleteInfo;
import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;

import java.util.ArrayList;

public class RiepilogoSchedaActivity extends AppCompatActivity {

    private ArrayList<Esercizio> esercizi = new ArrayList<Esercizio>();
    ListView lv;
    SchedaLogic schedaLogic;
    CustomAdapterEsercizi customAdapterEsercizi;
    SchedaEsercizi scheda;
    LoginRegistration loginRegistration;
    AthleteInfo athleteInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riepilogo_scheda);

        Bundle bundle = getIntent().getExtras();

        esercizi = (ArrayList<Esercizio>) bundle.getSerializable("Esercizi");

        lv = (ListView) findViewById(R.id.eserciziLV);
        schedaLogic = new SchedaLogic();

//        athleteInfo = new AthleteInfo();
//        loginRegistration = new LoginRegistration();
//
//        FirebaseUser user = loginRegistration.isUserLogged();
//
//        if (user != null)
//            recuperaAtleta(user.getEmail());

        //SchedaEsercizi scheda = new SchedaEsercizi(uid???,user.getDisplayName(),esercizi);

        customAdapterEsercizi = new CustomAdapterEsercizi(this, R.layout.list_esercizi_item, esercizi);

        lv.setAdapter(customAdapterEsercizi);

    }

    public void conferma(View v) {

//        schedaLogic.saveScheda(scheda);

        Intent intent = new Intent(getApplicationContext(), SchedaSuccess.class);
        startActivity(intent);

    }

    public void indietro(View v) {
        super.onBackPressed();
    }

//    private void recuperaAtleta(String email) {
//        Task<DocumentSnapshot> task = athleteInfo.getAthletebyEmail(email);
//
//        task.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                Atleta atleta = documentSnapshot.toObject(Atleta.class);
//                Log.d("DEBUG",atleta.getNome());
//            }
//        });
//    }

}

