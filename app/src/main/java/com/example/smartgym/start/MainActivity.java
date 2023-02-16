package com.example.smartgym.start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.smartgym.R;
import com.example.smartgym.gestioneScheda.application.activity.ExercisesFragment;
import com.example.smartgym.infoUtenti.application.activity.ActivityReceiver;
import com.example.smartgym.infoUtenti.application.activity.HomeFragment;
import com.example.smartgym.infoUtenti.application.activity.ProfileFragment;
import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

/**
 * Questa classe definisce l'activity principale dell'app dove è contenuto la bottom bar per la navigazione tra i vari fragment
 * che rappresentano le relative sezioni del sistema come la home, sezione esercizi e sezione del profilo. Implementa l'interfaccia
 * NavigationBarView.OnItemSelectedListener e ActivityReceiver
 */
public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener, ActivityReceiver {

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    ExercisesFragment exercisesFragment = new ExercisesFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    ActionBar actionBar;

    Atleta myAthlete;
    String idSchedaInUso = "";

    /**
     * Metodo di callback chiamato quando l'Activity viene creata.
     * In esso viene settato il layout dell'activity e la actionBar. Inoltre la bottom bar viene
     * collegata al metodo listener che si occupa di fare la transizione tra i fragment del menu.
     * Nell'activity sono mantenute inoltre alcune variabili globali richiamabili dai fragment
     * attraverso l'interfaccia ActivityReceiver quando avviene l'attach del fragment
     *
     * @param savedInstanceState oggetto Bundle contenente lo stato dell'Activity in caso di riavvio
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setTitle("HomePage");

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.homeFragment);
    }

    /**
     * Metodo listener chiamato quando si interagisce con la bottom bar che si occupa di effetuare
     * la transizione al fragment relativo alla voce selezionata
     *
     * @param item rappresenta una delle voci selezionata nella bottom bar
     * @return boolean, un valore true/false per stabilire se è stata selezionata una voce della bottom bar
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();

        switch (item.getItemId()) {
            case R.id.homeFragment:
                ft.replace(R.id.nav_fragment, homeFragment);
                actionBar.setTitle("HomePage");
                ft.commit();
                return true;

            case R.id.exercisesFragment:
                ft.replace(R.id.nav_fragment, exercisesFragment);
                actionBar.setTitle("Esercizi");
                ft.commit();
                return true;

            case R.id.profileFragment:
                ft.replace(R.id.nav_fragment, profileFragment);
                actionBar.setTitle("Profilo");
                ft.commit();
                return true;
        }

        return false;
    }

    /**
     {@inheritDoc}
     */
    @Override
    public String getIdSchedaInUso() {
        return idSchedaInUso;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIdSchedaInUso(String id) {
        idSchedaInUso = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Atleta getAtleta() {
        return myAthlete;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAtleta(Atleta atleta) {
        myAthlete = atleta;
    }
}