package com.example.smartgym.infoUtenti.application.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartgym.R;
import com.example.smartgym.gestioneScheda.application.activity.CustomAdapterEsercizi;
import com.example.smartgym.gestioneScheda.storage.entity.DettaglioEsercizio;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;
import com.example.smartgym.infoUtenti.application.logic.AthleteInfo;
import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;
import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.example.smartgym.start.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    TextView tv1;
    TextView tvUtente;

    CustomAdapterEsercizi customAdapterEsercizi;

    ListView lv;

    AthleteInfo athleteInfo;
    LoginRegistration loginRegistration;

    Atleta myAthlete;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv1 = view.findViewById(R.id.tv1);
        tvUtente = view.findViewById(R.id.tvUtente);
        lv = view.findViewById(R.id.lv1);

        loginRegistration = new LoginRegistration();
        athleteInfo = new AthleteInfo();

        FirebaseUser user = loginRegistration.isUserLogged();

        if (user != null)
            recuperaAtleta(user.getEmail());

        customAdapterEsercizi = new CustomAdapterEsercizi(getContext(),R.layout.list_esercizi_item,new ArrayList<Esercizio>());

        lv.setAdapter(customAdapterEsercizi);

        populateList();
    }

    private void recuperaAtleta(String email) {
        Task<DocumentSnapshot> task = athleteInfo.getAthletebyEmail(email);

        task.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Atleta atleta = documentSnapshot.toObject(Atleta.class);
                Log.d("DEBUG",atleta.getNome());
                saveAtleta(atleta);
            }
        });
    }

    private void saveAtleta(Atleta atleta) {
        myAthlete = atleta;

        tvUtente.setText("Benvenuto " + myAthlete.getNome());
    }

    private void populateList() {
        customAdapterEsercizi.add(new Esercizio("PushUp",new DettaglioEsercizio(10,0)));
        customAdapterEsercizi.add(new Esercizio("TricepDip",new DettaglioEsercizio(10,0)));
        customAdapterEsercizi.add(new Esercizio("Squat",new DettaglioEsercizio(10,0)));
        customAdapterEsercizi.add(new Esercizio("Trazioni",new DettaglioEsercizio(10,0)));
        customAdapterEsercizi.add(new Esercizio("Crunch",new DettaglioEsercizio(10,0)));
        customAdapterEsercizi.add(new Esercizio("Jumping Jacks",new DettaglioEsercizio(10,0)));
        customAdapterEsercizi.add(new Esercizio("Mountain Climber",new DettaglioEsercizio(10,0)));
        customAdapterEsercizi.add(new Esercizio("Plank",new DettaglioEsercizio(0,30)));
        customAdapterEsercizi.add(new Esercizio("Cobra Stretch",new DettaglioEsercizio(10,0)));
        customAdapterEsercizi.add(new Esercizio("Side Hop",new DettaglioEsercizio(0,20)));
    }

}