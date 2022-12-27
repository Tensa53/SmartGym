package com.example.smartgym.application;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartgym.R;
import com.example.smartgym.storage.Utente;
import com.example.smartgym.storage.UtenteDAO;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

public class HomeFragment extends Fragment {

    TextView tv1;
    TextView tvUtente;

    Utente myUtente;

    UtenteDAO utenteDAO;

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

        if (myUtente == null)
            recuperaUtente();
        else
            completeHome(null);

    }

    private void recuperaUtente() {
        String id = "giuseppeverdi@mail.it";

        utenteDAO = new UtenteDAO();

        DocumentReference docRef = utenteDAO.doRetrieveUserDocById(id);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Utente utente = documentSnapshot.toObject(Utente.class);
                completeHome(utente);
            }
        });
    }

    private void completeHome(Utente utente) {
        if (utente != null)
            myUtente = utente;

        tvUtente.setText("Benvenuto " + myUtente.getMail());
    }
}