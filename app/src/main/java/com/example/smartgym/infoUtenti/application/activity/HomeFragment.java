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
import com.example.smartgym.gestioneScheda.application.logic.GestioneSchedaService;
import com.example.smartgym.gestioneScheda.application.logic.GestioneSchedaServiceImpl;
import com.example.smartgym.gestioneScheda.storage.entity.DettaglioEsercizio;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;
import com.example.smartgym.gestioneScheda.storage.entity.RealScheda;
import com.example.smartgym.gestioneScheda.storage.entity.SchedaEsercizi;
import com.example.smartgym.infoUtenti.application.logic.AthleteInfo;
import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;
import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe HomeFragment rappresenta il fragment che gestisce la home dell'applicazione
 * dove viene visualizzato un messaggio di benvenuto, un'eventuale scheda fissata e il
 * pulsante di completa profilo nel caso non siano state inserite tutte le caratteristiche
 * dell'utente atleta. Sottoclasse di Fragment, implementa l'interfaccia View.OnClickListener
 */
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

    GestioneSchedaService gestioneSchedaService;

    /**
     * Costruttore vuoto per la classe HomeFragment
     */
    public HomeFragment() {
    }

    /**
     * Metodo che viene chiamato quando il fragment è stato creato.
     *
     * @param savedInstanceState bundle che contiene gli eventuali dati salvati
     *                           in precedenza dal fragment.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Metodo che viene chiamato quando il fragment è associato all'Activity.
     *
     * @param activity l'Activity a cui il fragment è associato.
     */
    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);

        activityReceiver = (ActivityReceiver) activity;
    }

    /**
     * Metodo che viene chiamato per creare e restituire la View gerarchia del layout associato
     * al fragment.
     *
     * @param inflater           il layoutInflater che viene utilizzato per gonfiare la view.
     * @param container          il ViewGroup a cui la view verrà eventualmente allegata.
     * @param savedInstanceState bundle che contiene gli eventuali dati salvati
     *                           in precedenza dal fragment.
     * @return la view creata per il fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    /**
     * Metodo chiamato subito dopo che il layout del fragment è stato creato.
     *
     * @param view               la view creata dal metodo onCreateView().
     * @param savedInstanceState bundle che contiene gli eventuali dati salvati
     *                           in precedenza dal fragment.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gestioneSchedaService = new GestioneSchedaServiceImpl();

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
    }

    /**
     * Metodo che viene chiamato per effettuare il binding dei widget del layout
     * con le rispettive istanze della classe a cui appartengono
     */
    private void widgetBinding() {
        tvUtente = getView().findViewById(R.id.tvUtente);
        btCompletaProfilo = getView().findViewById(R.id.btCompletaProfilo);
        tvSchedaInUso = getView().findViewById(R.id.tvSchedaInUso);
        lv = getView().findViewById(R.id.lv1);
    }

    /**
     * Questo metodo si occupa di recupera la scheda in uso attraverso il task restituito dalla classe
     * DAO. Sono richiamati i metodi setParametriScheda() e recuperaDettagli() per recuperare ulteriori
     * informazioni della scheda e salvare i dati nell'istanza della scheda presente nella classe. Inoltre
     * attraverso il metodo aggiornaSchedaInUso viene settato il nuovo id della scheda attraverso
     * ActivityReceiver
     */
    private void recuperaSchedaInUso() {
        Task<QuerySnapshot> task = gestioneSchedaService.getSchedaInUso(user.getUid());

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

    /**
     * Questo metodo viene chiamato da recuperaSchedaInUso() per memorizzare l'id della scheda
     * attualmente in uso e fissata nella home
     * @param id, l'identificativo della scheda
     */
    private void aggiornaSchedaInUso(String id) {
        idSchedaInUso = id;
        activityReceiver.setIdSchedaInUso(id);
    }

    /**
     * Questo metodo viene richiamato da recuperaSchedaInUso() per memorizzare le informazioni
     * relativi ai parametri della scheda nell'istanza presenza nella scheda
     *
     * @param realScheda, un'istanza della scheda presente nella classe
     */
    private void setParametriScheda(RealScheda realScheda) {
        ((RealScheda) schedaEsercizi).setModalita(realScheda.getModalita());
        ((RealScheda) schedaEsercizi).setNome(realScheda.getNome());
        ((RealScheda) schedaEsercizi).setInUso(realScheda.isInUso());
        ((RealScheda) schedaEsercizi).setPubblica(realScheda.isPubblica());
    }

    /**
     * Questo metodo viene chiamato da recuperaSchedaInUso() per recuperare i dettagli relativi
     * agli esercizi contenuti nella scheda esercizi. Viene richiamato il metodo recuperaEsercizio()
     * per recuperare l'esercizio legato al dettaglio appena recuperato
     *
     * @param dettaglies, una collezione dei riferimenti dei dettagli esercizi da recuperare dal db
     */
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

    /**
     * Questo metodo viene chiamato da recuperaDettagliScheda() per recuperare le informazioni relative
     * all'esercizio legato al relativo dettaglio. Viene richiamato il metodo aggiungiEsercizio() per
     * popolare la listview con l'esercizio appena recuperato
     *
     * @param dettaglioEsercizio, il dettaglio del relativo esercizio da recuperare
     * @param dr, il riferimento nel db dell'esercizio da recuperare
     */
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

    /**
     * Questo metodo viene richiamato da recuperaEsercizio() per popolare la listview
     * con l'esercizio appena recuperato
     *
     * @param esercizio, l'esercizio appena recuperato da aggiungere alla listview attraverso
     * il customapdapter utilizzato
     */
    private void aggiungiEsercizio(Esercizio esercizio) {
        ((RealScheda) schedaEsercizi).aggiungiEsercizio(esercizio);

        customAdapterEsercizi.add(esercizio);
    }

    /**
     * Questo metodo si occupa di recuperare le informazioni relative all'atleta attraverso il task
     * restituito dalla classe DAO. Viene richiamato il metodo saveAtleta() per memorizzare l'atleta\
     * nella variabile d'istanza della classe
     *
     * @param id, l'identificativo del documento dove sono memorizzate le informazioni dell'atleta
     */
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

    /**
     * Questo metodo viene richiamato da recuperaAtleta() per memorizzare le informazioni relative
     * all'atleta all'interno di una variabile di istanza
     *
     * @param atleta, un'istanza di atleta da memorizzare
     */
    private void saveAtleta(Atleta atleta) {
        myAthlete = atleta;

        activityReceiver.setAtleta(myAthlete);

        tvUtente.setText("Benvenuto " + myAthlete.getNome());

        if (myAthlete.areFeaturesEmpty())
            btCompletaProfilo.setVisibility(Button.VISIBLE);
    }

    /**
     * Questo metodo gestisce l'evento di click del pulsante specificato.
     *
     * @param view la vista che ha generato l'evento di click
     */
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btCompletaProfilo: onCompletProfiloClick();
        }
    }

    /**
     * Questo metodo viene richiamato da onClick() per gestire le operazioni relative al completamento
     * del profilo dell'atleta. Viene passata alla nuova activity lanciata, l'istanza dell'atleta
     * precedentemente recuperata dal db
     */
    private void onCompletProfiloClick() {
        Intent intent = new Intent(getContext(), InserimentoModificaCaratteristicheActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("User", myAthlete);
        intent.putExtras(b);
        startActivity(intent);
    }
}