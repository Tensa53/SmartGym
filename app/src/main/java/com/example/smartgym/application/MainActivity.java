package com.example.smartgym.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.smartgym.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

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