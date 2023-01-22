package com.example.smartgym.infoUtenti.application.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartgym.R;
import com.example.smartgym.infoUtenti.application.logic.AthleteInfo;
import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;
import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.example.smartgym.start.MainActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    Button btLogout;

    TextView tv1;

    Atleta myAthlete;

    LoginRegistration loginRegistration;
    AthleteInfo athleteInfo;

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btLogout = view.findViewById(R.id.btLogout);
        tv1 = view.findViewById(R.id.tv1);

        athleteInfo = new AthleteInfo();
        loginRegistration = new LoginRegistration();

        FirebaseUser user = loginRegistration.isUserLogged();

        if (user != null)
            recuperaAtleta(user.getEmail());

        btLogout.setOnClickListener(this);
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

        tv1.setText(myAthlete.toFormattedString());
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btLogout: onLogout();
            break;
        }
    }

    private void onLogout() {
        LoginRegistration loginRegistration = new LoginRegistration();
        loginRegistration.logout();

        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}