package com.example.smartgym.gestioneScheda.application.activity;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.smartgym.R;
import com.example.smartgym.gestioneScheda.application.logic.SchedaLogic;
import com.example.smartgym.gestioneScheda.storage.entity.ProxyScheda;
import com.example.smartgym.gestioneScheda.storage.entity.SchedaEsercizi;
import com.example.smartgym.infoUtenti.application.activity.ActivityReceiver;
import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * La classe ExercisesFragment rappresenta il fragment che gestisce la visualizzazione
 * delle schede degli esercizi. È una sottoclasse della classe Fragment e implementa
 * l'interfaccia View.OnClickListener e AdapterView.OnItemClickListener.
 */
public class ExercisesFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    Button bt1;

    ListView lv1;

    LoginRegistration loginRegistration;

    SchedaLogic schedaLogic;

    CustomAdapterNomeScheda customAdapter;

    ActivityReceiver activityReceiver;

    String idSchedaInUso = "";

    /**
     * Costruttore vuoto per la classe ExercisesFragment.
     */
    public ExercisesFragment() {
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
        return inflater.inflate(R.layout.fragment_exercises, container, false);
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

        idSchedaInUso = activityReceiver.getIdSchedaInUso();

        bt1 = getView().findViewById(R.id.bt1);

        lv1 = getView().findViewById(R.id.lv1);

        bt1.setOnClickListener(this);

        loginRegistration = new LoginRegistration();

        schedaLogic = new SchedaLogic();

        customAdapter = new CustomAdapterNomeScheda(getContext(), R.layout.list_nome_scheda_item, new ArrayList<SchedaEsercizi>());

        lv1.setAdapter(customAdapter);

        lv1.setOnItemClickListener(this);

        caricaSchede(loginRegistration.getUserLogged().getUid());
    }

    /**
     * Questo metodo carica tutte le schede dell'utente con l'id specificato.
     * Utilizza la schedaLogic per ottenere le schede, quindi aggiunge le schede all'adapter della ListView.
     * Infine, notifica l'adapter delle modifiche effettuate.
     *
     * @param id l'id dell'utente di cui caricare le schede.
     */
    private void caricaSchede(String id) {
        Task<QuerySnapshot> schedeResult = schedaLogic.getAllUserSchede(id);

        schedeResult.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("DEBUG", "" + task.getResult().size());
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String id = document.getId();
                        String nome = (String) document.get("nome");

                        Log.d("DEBUG", "ID SCHEDA: " + id);

                        Log.d("DEBUG", "NOME SCHEDA: " + nome);

                        SchedaEsercizi schedaEsercizi = new ProxyScheda(id, nome);

                        Log.d("DEBUG", "NOME PROXY SCHEDA: " + schedaEsercizi.getNome());

                        aggiungiScheda(schedaEsercizi);
                    }
                } else {
                    Toast.makeText(getContext(), "MANNAGGIAAA", Toast.LENGTH_SHORT).show();
                }
            }
        });

        customAdapter.notifyDataSetChanged();
    }

    /**
     * Questo metodo aggiunge la schedaEsercizi all'adapter della ListView.
     *
     * @param schedaEsercizi la scheda da aggiungere all'adapter
     */
    private void aggiungiScheda(SchedaEsercizi schedaEsercizi) {
        customAdapter.add(schedaEsercizi);
    }

    /**
     * Questo metodo gestisce l'evento di click del pulsante specificato.
     *
     * @param view la vista che ha generato l'evento di click
     */
    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.bt1:
                lanciaActivity1();
                break;
        }
    }

    /**
     * Questo metodo lancia l'activity "SelezioneModalitaSchedaActivity".
     * Passa una stringa extra all'activity per visualizzarla all'interno della stessa.
     */
    private void lanciaActivity1() {
        Intent i = new Intent(getContext(), SelezioneModalitaSchedaActivity.class);

        i.putExtra("stringa", "Seleziona la modalita di creazione della scheda: ");

        startActivity(i);
    }

    /**
     * Questo metodo gestisce l'evento di click su un elemento della ListView.
     * Lancia l'activity "VisualizzaSchedaEserciziActivity" passando l'id della scheda selezionata.
     *
     * @param adapterView la ListView in cui è stato effettuato il click
     * @param view        la vista selezionata
     * @param i           la posizione dell'elemento selezionato all'interno della ListView
     * @param l           l'id dell'elemento selezionato
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SchedaEsercizi schedaEsercizi = (SchedaEsercizi) lv1.getItemAtPosition(i);
        Log.d("DEBUG", schedaEsercizi.getNome());
        Intent intent = new Intent(getContext(), VisualizzaSchedaEserciziActivity.class);

        Log.d("DEBUG", "ID SCHEDA IN USO: " + idSchedaInUso);
        Log.d("DEBUG", "ID NUOVA SCHEDA IN USO: " + schedaEsercizi.getId());

        intent.putExtra("SCHEDAINUSO", idSchedaInUso);
        intent.putExtra("IDNUOVASCHEDA", schedaEsercizi.getId());
        Bundle bundle = new Bundle();
        bundle.putSerializable("PROXYSCHEDA", (ProxyScheda) schedaEsercizi);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}