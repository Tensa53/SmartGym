package com.example.smartgym.infoUtenti.application.activity;

import android.app.Activity;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    Button btModificaInfo, btModificaCaratteristiche, btLogout, btCancellaProfilo;

    TextView tvEmail, tvNome, tvCognome, tvDataDiNascita, tvSesso;

    TextView tvPeso, tvAltezza, tvAllenamenti, tvEsperienza;

    Atleta myAthlete;

    LoginRegistration loginRegistration;
    AthleteInfo athleteInfo;

    AtletaReceiver atletaReceiver;

    public ProfileFragment() {
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        atletaReceiver = (AtletaReceiver) activity;
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

        widgetBinding();

        athleteInfo = new AthleteInfo();
        loginRegistration = new LoginRegistration();
        String idUser = loginRegistration.getUserLogged().getUid();

        myAthlete = atletaReceiver.getAtleta();



//        if (idUser != null)
//            recuperaAtleta(idUser);

//        setFields();

        btModificaInfo.setOnClickListener(this);
        btModificaCaratteristiche.setOnClickListener(this);
        btLogout.setOnClickListener(this);
        btCancellaProfilo.setOnClickListener(this);
    }

    private void setFields() {
        tvEmail.append(" " + myAthlete.getEmail());
        tvNome.append(" " + myAthlete.getNome());
        tvCognome.append(" " + myAthlete.getCognome());
        tvDataDiNascita.append(" " + myAthlete.formattedDataDiNascita());
        tvSesso.append(" " + myAthlete.getSesso());

        tvPeso.append(" " + myAthlete.getPeso());
        tvAltezza.append(" " + myAthlete.getAltezza());
        tvAllenamenti.append(" " + myAthlete.getAllenamentiSettimanali());
        tvEsperienza.append(" " + myAthlete.getEsperienza());
    }

    private void widgetBinding() {

        btModificaInfo = getView().findViewById(R.id.btModificaInfo);
        btModificaCaratteristiche = getView().findViewById(R.id.btModificaCaratteristiche);
        btLogout = getView().findViewById(R.id.btLogout);
        btCancellaProfilo = getView().findViewById(R.id.btCancellaProfilo);

        tvEmail = getView().findViewById(R.id.tvEmail);
        tvNome = getView().findViewById(R.id.tvNome);
        tvCognome = getView().findViewById(R.id.tvCognome);
        tvDataDiNascita = getView().findViewById(R.id.tvDataDiNascita);
        tvSesso = getView().findViewById(R.id.tvSesso);

        tvPeso = getView().findViewById(R.id.tvPeso);
        tvAltezza = getView().findViewById(R.id.tvAltezza);
        tvAllenamenti = getView().findViewById(R.id.tvNumAllenamenti);
        tvEsperienza = getView().findViewById(R.id.tvEsperienza);
    }

    private void recuperaAtleta(String id) {
        Task<DocumentSnapshot> task = athleteInfo.getAthletebyId(id);

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
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btLogout: onLogout();
            break;
            case R.id.btModificaInfo: onModificaInfo();
            break;
            case R.id.btModificaCaratteristiche: onModificaCaratteristiche();
            break;
            case R.id.btCancellaProfilo: onCancellaProfilo();
            break;
        }
    }

    private void onCancellaProfilo() {
        Intent intent = new Intent(getContext(), CancellaProfiloActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("User", myAthlete);
        intent.putExtras(b);
        startActivity(intent);
    }

    private void onModificaCaratteristiche() {
        Intent intent = new Intent(getContext(), InserimentoModificaCaratteristicheActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("User", myAthlete);
        intent.putExtras(b);
        startActivity(intent);
    }

    private void onModificaInfo() {
        Intent intent = new Intent(getContext(), ModificaInfoAtletaActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("User", myAthlete);
        intent.putExtras(b);
        startActivity(intent);
    }


    private void onLogout() {
        LoginRegistration loginRegistration = new LoginRegistration();
        loginRegistration.logout();

        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}