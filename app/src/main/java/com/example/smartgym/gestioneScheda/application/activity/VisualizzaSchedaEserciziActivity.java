package com.example.smartgym.gestioneScheda.application.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartgym.R;
import com.example.smartgym.gestioneScheda.storage.dataAcess.EsercizioDAO;
import com.example.smartgym.gestioneScheda.storage.dataAcess.SchedaEserciziDAO;
import com.example.smartgym.gestioneScheda.storage.entity.DettaglioEsercizio;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;
import com.example.smartgym.gestioneScheda.storage.entity.ProxyScheda;
import com.example.smartgym.gestioneScheda.storage.entity.RealScheda;
import com.example.smartgym.gestioneScheda.storage.entity.SchedaEsercizi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class VisualizzaSchedaEserciziActivity extends AppCompatActivity implements View.OnClickListener {

    String nome;

    TextView tv1;

    EsercizioDAO esercizioDAO;

    SchedaEserciziDAO schedaEserciziDAO;

    SchedaEsercizi schedaEsercizi;

    CustomAdapterEsercizi customAdapterEsercizi;

    ListView lv;

    Button btFissa,btModifica,btCancella;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_scheda_esercizi);

        Bundle bundle = getIntent().getExtras();

        bundle.isEmpty();

        ProxyScheda proxyScheda = (ProxyScheda) bundle.getSerializable("PROXYSCHEDA");

        widgetBinding();

        tv1.setText(proxyScheda.getNome());

        btFissa.setOnClickListener(this);
        btModifica.setOnClickListener(this);
        btCancella.setOnClickListener(this);

        esercizioDAO = new EsercizioDAO();

        schedaEserciziDAO = new SchedaEserciziDAO();

        schedaEsercizi = new RealScheda();

        recuperaScheda(proxyScheda.getId());

        customAdapterEsercizi = new CustomAdapterEsercizi(this,R.layout.list_esercizi_item,new ArrayList<Esercizio>());

        lv.setAdapter(customAdapterEsercizi);
    }

    private void recuperaScheda(String id) {
        Task<DocumentSnapshot> task = schedaEserciziDAO.doRetrieveSchedaByDocumentId(id);

        task.addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot ds = task.getResult();
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
        });
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

    private void setParametriScheda(RealScheda realScheda) {
        ((RealScheda) schedaEsercizi).setModalita(realScheda.getModalita());
        ((RealScheda) schedaEsercizi).setNome(realScheda.getNome());
        ((RealScheda) schedaEsercizi).setInUso(realScheda.isInUso());
        ((RealScheda) schedaEsercizi).setPubblica(realScheda.isPubblica());
    }

    private void widgetBinding() {
        tv1 = findViewById(R.id.tv1);
        lv = findViewById(R.id.lv1);
        btFissa = findViewById(R.id.btFissaScheda);
        btModifica = findViewById(R.id.btModificaScheda);
        btCancella = findViewById(R.id.btCancellaScheda);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btFissaScheda: Toast.makeText(getApplicationContext(), "La scheda viene fissata nella home e considerata come in uso, TODO", Toast.LENGTH_SHORT).show();
            break;
            case R.id.btModificaScheda: onModifica();
            break;
            case R.id.btCancellaScheda: onCancella();
        }
    }

    private void onModifica() {
        Toast.makeText(getApplicationContext(), "TODO", Toast.LENGTH_SHORT).show();
    }

    private void onCancella() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE: Toast.makeText(getApplicationContext(), "TODO", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Sei sicuro di voler cancellare la scheda ?")
                .setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
}