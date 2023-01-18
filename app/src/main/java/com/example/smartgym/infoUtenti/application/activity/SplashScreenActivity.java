package com.example.smartgym.infoUtenti.application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.smartgym.R;
import com.example.smartgym.application.MainActivity;
import com.example.smartgym.infoUtenti.application.activity.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT = 1000;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FirebaseUser firebaseUser = mAuth.getCurrentUser();

                Intent intent;

                if (firebaseUser != null)
                    intent = new Intent(getApplicationContext(),MainActivity.class);
                else
                    intent = new Intent(getApplicationContext(), LoginActivity.class);

                startActivity(intent);

            }
        }, SPLASH_SCREEN_TIME_OUT);

    }
}