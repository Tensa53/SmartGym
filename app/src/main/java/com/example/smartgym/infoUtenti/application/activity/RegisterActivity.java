package com.example.smartgym.infoUtenti.application.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartgym.R;
import com.example.smartgym.infoUtenti.application.logic.AthleteInfo;
import com.example.smartgym.start.MainActivity;
import com.example.smartgym.infoUtenti.application.exception.LoginFieldException;
import com.example.smartgym.infoUtenti.application.exception.RegisterFieldException;
import com.example.smartgym.utils.FormUtils;
import com.example.smartgym.infoUtenti.application.logic.LoginRegistration;
import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;

import org.checkerframework.checker.units.qual.A;

import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,DatePickerDialog.OnDateSetListener {

    EditText etNome,etCognome,etEmail,etPassword,etRipetiPassword;

    Button btDataDiNascita, btRegistrati;

    TextView tvDataDiNascita;

    RadioGroup rbg1;

    LoginRegistration loginRegistration;

    FormUtils formUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        widgetBinding();

        btDataDiNascita.setOnClickListener(this);
        btRegistrati.setOnClickListener(this);

        loginRegistration = new LoginRegistration();
        formUtils = new FormUtils();
    }

    private void completeRegister(String email) {
        String nome = etNome.getText().toString();
        String cognome = etCognome.getText().toString();
        String dataDiNascita = tvDataDiNascita.getText().toString();

        int selectedRadio = rbg1.getCheckedRadioButtonId();

        try {
            formUtils.controllaAltriCampiRegistrazione(nome,cognome,dataDiNascita,selectedRadio);

            Date datadiNascita = formUtils.calcolaDataDiNascita(tvDataDiNascita.getTag().toString());

            RadioButton rb = findViewById(rbg1.getCheckedRadioButtonId());

            String sesso = rb.getText().toString();

            Atleta atleta = new Atleta(nome,cognome,email,sesso,datadiNascita);

            String idUser = loginRegistration.getUserLogged().getUid();

            Log.d("DEBUG", idUser.toString());

            AthleteInfo athleteInfo = new AthleteInfo();

            Log.d("DEBUG", athleteInfo.toString());

            Task<Void> registerResult = athleteInfo.saveAthlete(atleta,idUser);

            registerResult.addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful())
                        launchHome();
                }
            });
        } catch (RegisterFieldException e) {
            showError(e.getMessage());
        }
    }

    private void launchHome() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void onClickData() {
        final Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this,this,year,month,day);

        long eighteenYearsTimeTot = formUtils.getMaxDate();

        long maxDate = new Date().getTime() - eighteenYearsTimeTot;

        datePickerDialog.getDatePicker().setMaxDate(maxDate);
        datePickerDialog.show();
    }

    private void onRegister() {

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String ripetiPassword = etRipetiPassword.getText().toString();

        try {
            formUtils.controllaEmailEPassword(email,password,ripetiPassword);

            Task<AuthResult> createResult = loginRegistration.createUser(email,password);

            createResult.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                        completeRegister(email);
                    else
                        etEmail.setError("Indirizzo email gia in uso");
                }
            });
        } catch (LoginFieldException e) {
            showError(e.getMessage());
        }
    }

    private void showError(String error) {
        String id = error.split("_")[0];
        String msg = error.split("_")[1];

        Log.d("DEBUG",id.substring(0,2));

        if (id.substring(0,2).equals("et")){
            int rid = getResources().getIdentifier(id, "id", getPackageName());

            EditText et = findViewById(rid);

            et.setError(msg);
        } else
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

    }

    private void widgetBinding() {
        etNome = findViewById(R.id.etNome);
        etCognome = findViewById(R.id.etCognome);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etRipetiPassword = findViewById(R.id.etPasswordConf);
        btDataDiNascita = findViewById(R.id.btDataDiNascita);
        tvDataDiNascita = findViewById(R.id.tvDataDiNascita);
        btRegistrati = findViewById(R.id.btRegistrati);
        rbg1 = findViewById(R.id.rbg1);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btDataDiNascita: onClickData();
                break;
            case R.id.btRegistrati: onRegister();
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        tvDataDiNascita.setTag(i2 + "-" + i1 + "-" + i);

        if (i2 < 10 && i1 < 10)
            tvDataDiNascita.setText("0" + i2 + "-" + "0" + (i1 + 1) + "-" + i);

        if (i2 < 10 && i1 >= 10)
            tvDataDiNascita.setText("0" + i2 + "-" + (i1 + 1) + "-" + i);

        if (i2 >= 10 && i1 < 10)
            tvDataDiNascita.setText(i2 + "-" + "0" + (i1 + 1) + "-" + i);
    }
}