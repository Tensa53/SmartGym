package com.example.smartgym.infoUtenti.application.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartgym.R;
import com.example.smartgym.gestioneScheda.application.activity.CustomAdapterEsercizi;
import com.example.smartgym.gestioneScheda.storage.dataAcess.EsercizioDAO;
import com.example.smartgym.gestioneScheda.storage.dataAcess.SchedaEserciziDAO;
import com.example.smartgym.gestioneScheda.storage.entity.DettaglioEsercizio;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;
import com.example.smartgym.gestioneScheda.storage.entity.RealScheda;
import com.example.smartgym.gestioneScheda.storage.entity.SchedaEsercizi;
import com.example.smartgym.infoUtenti.application.logic.AthleteInfo;
import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;
import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.example.smartgym.start.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {

    TextView tvUtente, tvSchedaInUso;

    Button btCompletaProfilo;

    CustomAdapterEsercizi customAdapterEsercizi;

    ListView lv;

    AthleteInfo athleteInfo;
    LoginRegistration loginRegistration;

    SchedaEsercizi schedaEsercizi;

    String idSchedaInUso = "";

    Atleta myAthlete;
    FirebaseUser user;
    ActivityReceiver activityReceiver;

    SchedaEserciziDAO schedaEserciziDAO;
    EsercizioDAO esercizioDAO;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);

        activityReceiver = (ActivityReceiver) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        schedaEserciziDAO = new SchedaEserciziDAO();
        esercizioDAO = new EsercizioDAO();

        schedaEsercizi = new RealScheda();

        widgetBinding();

        btCompletaProfilo.setOnClickListener(this);

        loginRegistration = new LoginRegistration();
        athleteInfo = new AthleteInfo();
        user = loginRegistration.getUserLogged();

        if (user != null)
            recuperaAtleta(user.getUid());

        customAdapterEsercizi = new CustomAdapterEsercizi(getContext(),R.layout.list_esercizi_item,new ArrayList<Esercizio>());

        recuperaSchedaInUso();

        lv.setAdapter(customAdapterEsercizi);

        tvSchedaInUso.setText("Scheda Esercizi fissata: ");
//
//        populateList();
    }

    private void widgetBinding() {
        tvUtente = getView().findViewById(R.id.tvUtente);
        btCompletaProfilo = getView().findViewById(R.id.btCompletaProfilo);
        tvSchedaInUso = getView().findViewById(R.id.tvSchedaInUso);
        lv = getView().findViewById(R.id.lv1);
    }

    private void recuperaSchedaInUso() {
        Task<QuerySnapshot> task = schedaEserciziDAO.doRetrieveSchedaInUso(user.getUid());

        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().size() > 0){
                        QuerySnapshot qs = task.getResult();
                        List<DocumentSnapshot> dss = qs.getDocuments();
                        DocumentSnapshot ds = dss.get(0);
                        aggiornaSchedaInUso(ds.getId());
                        String modalita = (String) ds.get("modalita");
                        String nome = (String) ds.get("nome");
                        boolean inUso = (boolean) ds.get("inUso");
                        boolean pubblica = (boolean) ds.get("pubblica");
                        ArrayList<DocumentReference> dettaglies = (ArrayList<DocumentReference>) ds.get("esercizi_scelti");

                        RealScheda realScheda = new RealScheda();
                        realScheda.setNome(nome);
                        realScheda.setInUso(inUso);
                        realScheda.setPubblica(pubblica);
                        realScheda.setModalita(modalita);

                        setParametriScheda(realScheda);

                        recuperaDettagliScheda(dettaglies);
                    }
                }
            }
        });
    }

    private void aggiornaSchedaInUso(String id) {
        idSchedaInUso = id;
        activityReceiver.setIdSchedaInUso(id);
    }

    private void recuperaDettagliScheda(ArrayList<DocumentReference> dettaglies) {

        for (DocumentReference dr: dettaglies) {
            Task<DocumentSnapshot> task = dr.get();

            task.addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot ds = task.getResult();

                        DocumentReference dr = (DocumentReference) ds.get("esercizio");
                        Long durata = (Long) ds.get("durata");
                        Integer durataInt = Math.toIntExact(durata);
                        Long ripetizioni = (Long) ds.get("ripetizioni");
                        Integer ripetizioniInt = Math.toIntExact(ripetizioni);

                        DettaglioEsercizio dettaglioEsercizio = new DettaglioEsercizio(ripetizioniInt, durataInt);

                        recuperaEsercizio(dettaglioEsercizio, dr);
                    }
                }
            });
        }

    }

    private void recuperaEsercizio(DettaglioEsercizio dettaglioEsercizio, DocumentReference dr) {

        Task<DocumentSnapshot> task = dr.get();

        task.addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot ds = task.getResult();

                    String esecuzione = (String) ds.get("esecuzione");
                    String nome = (String) ds.get("nome");
                    String descrizione = (String) ds.get("descrizione");
                    String parteDelCorpo = (String) ds.get("parteDelCorpo");
                    String tipologia = (String) ds.get("tipologia");
                    String difficolta = (String) ds.get("difficolta");

                    Esercizio esercizio = new Esercizio(nome,descrizione, difficolta, parteDelCorpo, tipologia, esecuzione, dettaglioEsercizio);

                    aggiungiEsercizio(esercizio);
                }
            }
        });

        customAdapterEsercizi.notifyDataSetChanged();

    }

    private void aggiungiEsercizio(Esercizio esercizio) {
        ((RealScheda) schedaEsercizi).aggiungiEsercizio(esercizio);

        customAdapterEsercizi.add(esercizio);
    }

    private void mostraAvviso() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE:
                    {
                        Toast.makeText(getContext(),"OKAY", Toast.LENGTH_LONG).show();
                    }
                    break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Informazioni aggiornate con successo !")
                .setPositiveButton("OK", dialogClickListener).show();
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

    private void setParametriScheda(RealScheda realScheda) {
        ((RealScheda) schedaEsercizi).setModalita(realScheda.getModalita());
        ((RealScheda) schedaEsercizi).setNome(realScheda.getNome());
        ((RealScheda) schedaEsercizi).setInUso(realScheda.isInUso());
        ((RealScheda) schedaEsercizi).setPubblica(realScheda.isPubblica());
    }

    private void saveAtleta(Atleta atleta) {
        myAthlete = atleta;

        activityReceiver.setAtleta(myAthlete);

        tvUtente.setText("Benvenuto " + myAthlete.getNome());

        if (myAthlete.areFeaturesEmpty())
            btCompletaProfilo.setVisibility(Button.VISIBLE);
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

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btCompletaProfilo: onCompletProfiloClick();
        }
    }

    private void onCompletProfiloClick() {
        Intent intent = new Intent(getContext(), InserimentoModificaCaratteristicheActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("User", myAthlete);
        intent.putExtras(b);
        startActivity(intent);
    }
}