package com.example.smartgym.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.smartgym.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getActionBar();

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.homeFragment);
    }

    HomeFragment homeFragment = new HomeFragment();
    ExercisesFragment exercisesFragment = new ExercisesFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.homeFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment, homeFragment).commit();
                return true;
            case R.id.exercisesFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment, exercisesFragment).commit();
                return true;
            case R.id.profileFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment, profileFragment).commit();
                return true;
        }

        return false;
    }
}