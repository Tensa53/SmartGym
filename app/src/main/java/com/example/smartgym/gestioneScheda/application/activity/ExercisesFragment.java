package com.example.smartgym.gestioneScheda.application.activity;

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

import com.example.smartgym.R;
import com.example.smartgym.gestioneScheda.storage.dataAcess.SchedaEserciziDAO;
import com.example.smartgym.gestioneScheda.storage.entity.SchedaEsercizi;
import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;

import java.util.ArrayList;

public class ExercisesFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    Button bt1;

    ListView lv1;

    LoginRegistration loginRegistration;

    SchedaEserciziDAO schedaEserciziDAO;

    CustomAdapterNomeScheda customAdapter;

    public ExercisesFragment() {
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

        customAdapter = new CustomAdapterNomeScheda(getContext(),R.layout.list_nome_scheda_item,new ArrayList<SchedaEsercizi>());

        lv1.setAdapter(customAdapter);

        lv1.setOnItemClickListener(this);

        caricaSchede(loginRegistration.isUserLogged().getEmail());
    }

    private void caricaSchede(String email) {
//        Task<QuerySnapshot> schedeResult = schedaEserciziDAO.doRetrieveAllSchedeByUserEmail(email);
//
//        schedeResult.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()){
//                    Log.d("DEBUG",""+task.getResult().size());
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        SchedaEsercizi schedaEsercizi = new SchedaEsercizi();
//                        schedaEsercizi.setId(document.getId());
//                        schedaEsercizi.setNome(document.get("nome").toString());
//                        schedaEsercizi.setModalita(document.get("modalita").toString());
//                        schedaEsercizi.setInUso((Boolean) document.get("inUso"));
//                        schedaEsercizi.setPubblica((Boolean) document.get("pubblica"));
//                        Log.d("DEBUG","NOME SCHEDA: "+ schedaEsercizi.getNome());
//                        Log.d("DEBUG","MODALITA SCHEDA: " + schedaEsercizi.getModalita());
//                        aggiungiScheda(schedaEsercizi);
//                    }
//                } else {
//                    Toast.makeText(getContext(),"MANNAGGIAAA",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        for (int i = 1; i <= 10; i++) {
            String nomeScheda = "Scheda"+i;
            aggiungiScheda(new SchedaEsercizi(nomeScheda));
        }

    }

    private void aggiungiScheda(SchedaEsercizi schedaEsercizi) {
        customAdapter.add(schedaEsercizi);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.bt1: lanciaActivity1(); break;
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
        Log.d("DEBUG",schedaEsercizi.getNome());
        Intent intent = new Intent(getContext(), VisualizzaSchedaEserciziActivity.class);
        intent.putExtra("NOMESCHEDA",schedaEsercizi.getNome());
        startActivity(intent);
    }
}