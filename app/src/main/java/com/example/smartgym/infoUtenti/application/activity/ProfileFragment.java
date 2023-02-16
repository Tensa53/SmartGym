package com.example.smartgym.infoUtenti.application.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartgym.R;
import com.example.smartgym.infoUtenti.application.logic.AthleteInfo;
import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;
import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.google.firebase.auth.FirebaseUser;

/**
 * La classe ProfileFragment rappresenta il fragment che gestisce la sezione profilo
 * dell'applicazione e da dove è possibile accedere alla modifica informazioni atleta, inserimento
 * e/o caratteristiche atleta, effettuare il logout e cancellare il profilo. Sottoclasse di
 * Fragment, implementa View.OnClickListener
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {

    Button btModificaInfo, btModificaCaratteristiche, btLogout, btCancellaProfilo;

    TextView tvEmail, tvNome, tvCognome, tvDataDiNascita, tvSesso;

    TextView tvPeso, tvAltezza, tvAllenamenti, tvEsperienza;

    Atleta myAthlete;

    ActivityReceiver activityReceiver;

    String idSchedaInUso = "";

    LoginRegistration loginRegistration;
    AthleteInfo athleteInfo;

    /**
     * Costruttore vuoto per la classe HomeFragment
     */
    public ProfileFragment() {
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
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

        widgetBinding();

        athleteInfo = new AthleteInfo();
        loginRegistration = new LoginRegistration();
        FirebaseUser user = loginRegistration.getUserLogged();

        myAthlete = activityReceiver.getAtleta();

        setFields();

        btModificaInfo.setOnClickListener(this);
        btModificaCaratteristiche.setOnClickListener(this);
        btLogout.setOnClickListener(this);
        btCancellaProfilo.setOnClickListener(this);
    }

    /**
     * Questo metodo viene chiamato da OnCreate() per settare i campi del form dopo aver recuperato
     * l'atleta dall'intent
     */
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

    /**
     * Metodo che viene chiamato per effettuare il binding dei widget del layout
     * con le rispettive istanze della classe a cui appartengono
     */
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

    /**
     * Questo metodo gestisce l'evento di click del pulsante specificato.
     *
     * @param view la vista che ha generato l'evento di click
     */
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

    /**
     * Questo metodo viene richiamato da onClick() per gestire le operazioni di cancellazioni
     * del profilo "TODO".
     */
    private void onCancellaProfilo() {
        mostraAvviso();
    }

    /**
     * Questo metodo viene richiamato da cancellaProfilo() per chiedere ulteriore conferma
     * all'utente della cancellazione
     */
    private void mostraAvviso() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE: Toast.makeText(getContext(), "TODO", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setMessage("Sei sicuro di voler cancellare il profilo ?")
                .setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    /**
     * Questo metodo viene richiamato da onClick() per gestire le operazioni relative
     * all'inserimento e/o modifica caratteristiche dell'atleta. Viene richiamata la relativa
     * activity che si occupa delle suddette operazioni e tramite intent viene passato l'atleta
     */
    private void onModificaCaratteristiche() {
        Intent intent = new Intent(getContext(), InserimentoModificaCaratteristicheActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("User", myAthlete);
        intent.putExtras(b);
        startActivity(intent);
    }

    /**
     * Questo metodo viene richiamato da onClick() per gestire le operazioni relative
     * alla modifica informazioni dell'atleta. Viene richiamata la relativa
     * activity che si occupa delle suddette operazioni e tramite intent viene passato l'atleta
     */
    private void onModificaInfo() {
        Intent intent = new Intent(getContext(), ModificaInfoAtletaActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("User", myAthlete);
        intent.putExtras(b);
        startActivity(intent);
    }

    /**
     * Questo metodo viene richiamato da onClick() per gestire le operazioni di logout attraverso
     * l'utilizzo del servizio di autenticazione tramite la classe LoginRegistration. Viene inoltre
     * richiamata l'activity di login.
     */
    private void onLogout() {
        LoginRegistration loginRegistration = new LoginRegistration();
        loginRegistration.logout();

        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}