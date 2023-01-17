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
import com.example.smartgym.infoUtenti.storage.Utente;
import com.example.smartgym.infoUtenti.storage.UtenteDAO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        utenteDAO = new UtenteDAO();

        Task<QuerySnapshot> task = utenteDAO.doRetrieveUserDocByEmail(email);

        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                    Utente utente = documentSnapshot.toObject(Utente.class);
                    completeHome(utente);
                } else {

                }
            }
        });

    }

    private void completeHome(Utente utente) {
        if (utente != null)
            myUtente = utente;

        tvUtente.setText("Benvenuto " + myUtente.getMail());
    }
}