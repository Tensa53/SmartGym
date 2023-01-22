package com.example.smartgym.start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.smartgym.R;
import com.example.smartgym.gestioneScheda.application.activity.ExercisesFragment;
import com.example.smartgym.infoUtenti.application.activity.HomeFragment;
import com.example.smartgym.infoUtenti.application.activity.ProfileFragment;
import com.example.smartgym.infoUtenti.application.logic.AthleteInfo;
import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;
import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.security.AlgorithmConstraints;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    ExercisesFragment exercisesFragment = new ExercisesFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    ActionBar actionBar;

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
}