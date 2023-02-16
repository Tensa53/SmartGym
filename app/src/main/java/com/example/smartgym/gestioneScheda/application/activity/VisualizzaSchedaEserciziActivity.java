package com.example.smartgym.gestioneScheda.application.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartgym.R;
import com.example.smartgym.gestioneScheda.application.logic.SchedaLogic;
import com.example.smartgym.gestioneScheda.storage.entity.DettaglioEsercizio;
import com.example.smartgym.gestioneScheda.storage.entity.Esercizio;
import com.example.smartgym.gestioneScheda.storage.entity.ProxyScheda;
import com.example.smartgym.gestioneScheda.storage.entity.RealScheda;
import com.example.smartgym.gestioneScheda.storage.entity.SchedaEsercizi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

/**
 * Questa classe definisce l'Activity per la visualizzazione di una scheda di esercizi.
 * Si occupa di mostrare a video tutti gli esercizi presenti in una scheda selezionata dall'utente.
 * Inoltre, permette di effettuare tre azioni principali: fissare, modificare o cancellare la scheda in uso.
 */
public class VisualizzaSchedaEserciziActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv1;

    String idSchedaInUso;

    String idNuovaSchedaInuso;

    SchedaLogic schedaLogic;

    SchedaEsercizi schedaEsercizi;

    CustomAdapterEsercizi customAdapterEsercizi;

    ListView lv;

    Button btFissa, btModifica, btCancella;

    /**
     * Il metodo onCreate() viene chiamato all'avvio dell'Activity.
     * Si occupa di inizializzare i campi della classe e di caricare la lista degli esercizi
     * della scheda selezionata.
     * Inoltre, ascolta gli eventi generati dai pulsanti dell'Activity e richiama il relativo metodo
     * in base al pulsante premuto.
     *
     * @param savedInstanceState Oggetto di tipo Bundle che rappresenta lo stato dell'Activity in un preciso momento.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_scheda_esercizi);

        Bundle bundle = getIntent().getExtras();

        bundle.isEmpty();

        ProxyScheda proxyScheda = (ProxyScheda) bundle.getSerializable("PROXYSCHEDA");

        idSchedaInUso = getIntent().getStringExtra("SCHEDAINUSO");

        Log.d("DEBUG", idSchedaInUso);

        idNuovaSchedaInuso = getIntent().getStringExtra("IDNUOVASCHEDA");

        Log.d("DEBUG", idNuovaSchedaInuso);

        widgetBinding();

        tv1.setText(proxyScheda.getNome());

        btFissa.setOnClickListener(this);
        btModifica.setOnClickListener(this);
        btCancella.setOnClickListener(this);

        schedaLogic = new SchedaLogic();

        schedaEsercizi = new RealScheda();

        recuperaScheda(proxyScheda.getId());

        customAdapterEsercizi = new CustomAdapterEsercizi(this, R.layout.list_esercizi_item, new ArrayList<Esercizio>());

        lv.setAdapter(customAdapterEsercizi);
    }

    /**
     * Il metodo recuperaScheda() recupera dal database Firebase la scheda di esercizi
     * selezionata dall'utente tramite il suo ID e setta i parametri dell'oggetto di tipo RealScheda
     * utilizzato dalla classe.
     *
     * @param id ID della scheda di esercizi da recuperare.
     */
    private void recuperaScheda(String id) {
        Task<DocumentSnapshot> task = schedaLogic.getSchedaById(id);

        task.addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
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

    /**
     * Recupera i dettagli della scheda di allenamento dal database e li visualizza a schermo.
     * Questo metodo viene chiamato quando l'activity viene creata o ripristinata dallo stato precedente.
     * Se l'utente ha eseguito l'accesso, il metodo recupera i dati della scheda di allenamento dall'ID passato
     * tramite l'intent. Successivamente, recupera i dati degli esercizi presenti nella scheda e li visualizza
     * a schermo mediante la creazione di un'istanza dell'adapter della lista degli esercizi e l'impostazione
     * di tale adapter sulla ListView.
     * Se l'utente non ha eseguito l'accesso, il metodo visualizza un messaggio di errore a schermo.
     */
    private void recuperaDettagliScheda(ArrayList<DocumentReference> dettaglies) {

        for (DocumentReference dr : dettaglies) {
            Task<DocumentSnapshot> task = dr.get();

            task.addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
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
     * Recupera le informazioni di un esercizio dal database e crea un oggetto Esercizio quindi lo aggiunge alla scheda.
     *
     * @param dettaglioEsercizio il dettaglio dell'esercizio da aggiungere alla scheda
     * @param dr                 il riferimento del documento contenente le informazioni dell'esercizio da recuperare dal database
     */
    private void recuperaEsercizio(DettaglioEsercizio dettaglioEsercizio, DocumentReference dr) {

        Task<DocumentSnapshot> task = dr.get();

        task.addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot ds = task.getResult();

                    String esecuzione = (String) ds.get("esecuzione");
                    String nome = (String) ds.get("nome");
                    String descrizione = (String) ds.get("descrizione");
                    String parteDelCorpo = (String) ds.get("parteDelCorpo");
                    String tipologia = (String) ds.get("tipologia");
                    String difficolta = (String) ds.get("difficolta");

                    Esercizio esercizio = new Esercizio(nome, descrizione, difficolta, parteDelCorpo, tipologia, esecuzione, dettaglioEsercizio);

                    aggiungiEsercizio(esercizio);
                }
            }
        });

        customAdapterEsercizi.notifyDataSetChanged();

    }

    /**
     * Aggiunge l'esercizio passato come parametro alla scheda e al CustomAdapter.
     *
     * @param esercizio l'Esercizio da aggiungere alla scheda
     */
    private void aggiungiEsercizio(Esercizio esercizio) {
        ((RealScheda) schedaEsercizi).aggiungiEsercizio(esercizio);

        customAdapterEsercizi.add(esercizio);
    }

    /**
     * Imposta i parametri della scheda a quelli della scheda passata come parametro.
     *
     * @param realScheda la RealScheda di cui impostare i parametri nella scheda corrente
     */
    private void setParametriScheda(RealScheda realScheda) {
        ((RealScheda) schedaEsercizi).setModalita(realScheda.getModalita());
        ((RealScheda) schedaEsercizi).setNome(realScheda.getNome());
        ((RealScheda) schedaEsercizi).setInUso(realScheda.isInUso());
        ((RealScheda) schedaEsercizi).setPubblica(realScheda.isPubblica());
    }

    /**
     * Associa le view della schermata ai relativi oggetti del layout.
     */
    private void widgetBinding() {
        tv1 = findViewById(R.id.tv1);
        lv = findViewById(R.id.lv1);
        btFissa = findViewById(R.id.btFissaScheda);
        btModifica = findViewById(R.id.btModificaScheda);
        btCancella = findViewById(R.id.btCancellaScheda);
    }

    /**
     * Metodo che viene chiamato quando un pulsante della schermata viene cliccato.
     *
     * @param view la vista cliccata
     */
    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btFissaScheda:
                onFissaScheda();
                break;
            case R.id.btModificaScheda:
                onModifica();
                break;
            case R.id.btCancellaScheda:
                onCancella();
        }
    }

    /**
     * Metodo privato che viene chiamato quando il pulsante "Fissa Scheda" viene cliccato.
     * Se l'ID della scheda in uso non è vuoto, viene prima impostata come non in uso tramite un Task.
     * Successivamente, viene fissata la nuova scheda selezionata tramite un altro Task.
     */
    private void onFissaScheda() {
        if (!idSchedaInUso.isEmpty()) {
            Task<Void> task = schedaLogic.setSchedaInUso(idSchedaInUso, "inUso", false);

            task.addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    fissaNuovaScheda();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else
            fissaNuovaScheda();
    }

    /**
     * Metodo privato che fissa la nuova scheda selezionata impostandola come in uso tramite un Task.
     */
    private void fissaNuovaScheda() {
        Task<Void> task = schedaLogic.setSchedaInUso(idNuovaSchedaInuso, "inUso", true);

        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Scheda Fissata nella home", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Metodo privato che viene chiamato quando il pulsante "Modifica Scheda" viene cliccato.
     * Mostra un messaggio "TODO".
     */
    private void onModifica() {
        Toast.makeText(getApplicationContext(), "TODO", Toast.LENGTH_SHORT).show();
    }

    /**
     * Metodo privato che viene chiamato quando il pulsante "Cancella Scheda" viene cliccato.
     * Mostra una finestra di dialogo per chiedere la conferma dell'eliminazione della scheda.
     * Se l'utente conferma, viene mostrato un messaggio "TODO".
     */
    private void onCancella() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Toast.makeText(getApplicationContext(), "TODO", Toast.LENGTH_SHORT).show();
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