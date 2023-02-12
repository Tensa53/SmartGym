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
import com.example.smartgym.gestioneScheda.storage.dataAcess.SchedaEserciziDAO;
import com.example.smartgym.gestioneScheda.storage.entity.ProxyScheda;
import com.example.smartgym.gestioneScheda.storage.entity.RealScheda;
import com.example.smartgym.gestioneScheda.storage.entity.SchedaEsercizi;
import com.example.smartgym.infoUtenti.application.activity.AtletaReceiver;
import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ExercisesFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    Button bt1;

    ListView lv1;

    LoginRegistration loginRegistration;

    SchedaEserciziDAO schedaEserciziDAO;

    CustomAdapterNomeScheda customAdapter;

    AtletaReceiver atletaReceiver;

    public ExercisesFragment() {
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
        return inflater.inflate(R.layout.fragment_exercises, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bt1 = getView().findViewById(R.id.bt1);

        lv1 = getView().findViewById(R.id.lv1);

        bt1.setOnClickListener(this);

        loginRegistration = new LoginRegistration();

        schedaEserciziDAO = new SchedaEserciziDAO();

        customAdapter = new CustomAdapterNomeScheda(getContext(), R.layout.list_nome_scheda_item, new ArrayList<SchedaEsercizi>());

        lv1.setAdapter(customAdapter);

        lv1.setOnItemClickListener(this);

        caricaSchede(loginRegistration.getUserLogged().getUid());
    }

    private void caricaSchede(String id) {
        Task<QuerySnapshot> schedeResult = schedaEserciziDAO.doRetrieveAllSchedeByUserId(id);

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

                        SchedaEsercizi schedaEsercizi = new ProxyScheda(id,nome);

                        Log.d("DEBUG", "NOME PROXY SCHEDA: " + schedaEsercizi.getNome());


                        aggiungiScheda(schedaEsercizi);
                    }
                } else {
                    Toast.makeText(getContext(), "MANNAGGIAAA", Toast.LENGTH_SHORT).show();
                }
            }
        });

        customAdapter.notifyDataSetChanged();

//        for (int i = 1; i <= 10; i++) {
//            String nomeScheda = "Scheda"+i;
//            aggiungiScheda(new SchedaEsercizi(nomeScheda));
//               }

    }

    private void aggiungiScheda(SchedaEsercizi schedaEsercizi) {
        customAdapter.add(schedaEsercizi);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.bt1:
                lanciaActivity1();
                break;
        }
    }

    private void lanciaActivity1() {
        Intent i = new Intent(getContext(), SelezioneModalitaSchedaActivity.class);

        i.putExtra("stringa", "Seleziona la modalita di creazione della scheda: ");

        startActivity(i);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SchedaEsercizi schedaEsercizi = (SchedaEsercizi) lv1.getItemAtPosition(i);
        Log.d("DEBUG", schedaEsercizi.getNome());
        Intent intent = new Intent(getContext(), VisualizzaSchedaEserciziActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("PROXYSCHEDA", (ProxyScheda) schedaEsercizi);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}