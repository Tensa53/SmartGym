package com.example.smartgym.infoUtenti.application.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartgym.R;
import com.example.smartgym.application.MainActivity;
import com.example.smartgym.infoUtenti.application.service.InfoUtentiService;
import com.example.smartgym.infoUtenti.application.service.InfoUtentiServiceImpl;
import com.example.smartgym.infoUtenti.storage.entity.Utente;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,DatePickerDialog.OnDateSetListener {

    EditText etNome,etCognome,etEmail,etPassword,etRipetiPassword;

    Button btDataDiNascita, btRegistrati;

    TextView tvDataDiNascita;

    RadioGroup rbg1;

    InfoUtentiServiceImpl infoUtentiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        widgetBinding();

        btDataDiNascita.setOnClickListener(this);
        btRegistrati.setOnClickListener(this);

        infoUtentiService = new InfoUtentiServiceImpl();
    }

    private void completeRegister(String email) {

        if (controllaAltriCampi()){
            String nome = etNome.getText().toString();

            String cognome = etCognome.getText().toString();

            Timestamp datadiNascita = calcolaDataDiNascita();

            RadioButton rb = findViewById(rbg1.getCheckedRadioButtonId());

            boolean sesso = Boolean.parseBoolean(rb.getTag().toString());

            Utente utente = new Utente(nome,cognome,email,sesso,datadiNascita);

            Task<DocumentReference> saveResult = infoUtentiService.registerUserData(utente);

            saveResult.addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    launchHome();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean controllaAltriCampi() {
        String nome = etNome.getText().toString();

        if (nome.length() == 0){
            Toast.makeText(this,"Inserisci un nome",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (nome.length() > 20){
            Toast.makeText(this,"Il nome non deve superare i 20 caratteri",Toast.LENGTH_SHORT).show();
            return false;
        }

        String cognome = etCognome.getText().toString();

        if (cognome.length() == 0){
            Toast.makeText(this,"Inserisci un cognome",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (cognome.length() > 20){
            Toast.makeText(this,"Il cognome non deve superare i 20 caratteri",Toast.LENGTH_SHORT).show();
            return false;
        }

        String dataDiNascita = tvDataDiNascita.getText().toString();

        if (dataDiNascita.compareTo("DD-MM-YYYY") == 0){
            Toast.makeText(this, "Inserisci una data di nascita",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (rbg1.getCheckedRadioButtonId() == -1){
            Toast.makeText(this,"Seleziona il sesso",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private Timestamp calcolaDataDiNascita() {
        String dataDiNascita = tvDataDiNascita.getTag().toString();

        int day = Integer.parseInt(dataDiNascita.split("-")[0]);

        int month = Integer.parseInt(dataDiNascita.split("-")[1]) - 1;

        int year = Integer.parseInt(dataDiNascita.split("-")[2]);

        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, day);

        Date date = new Date(gregorianCalendar.getTimeInMillis());

        return new Timestamp(date);
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

        long yearTime;

        long yearTimeTot = 0L;

        int yeard = Calendar.getInstance().get(Calendar.YEAR);

        for (int i = yeard; i > (yeard - 18); i--){
            if (i % 4 == 0)
                yearTime = 1000L*60*60*24*366;
            else
                yearTime = 1000L*60*60*24*365;

            yearTimeTot += yearTime;
        }

        long maxDate = new Date().getTime() - yearTimeTot;

        datePickerDialog.getDatePicker().setMaxDate(maxDate);
        datePickerDialog.show();
    }

    private void onRegister() {
        String email = etEmail.getText().toString();

        String password = etPassword.getText().toString();

        if (controllaEmailEPassword(email,password)) {
            Task<AuthResult> createResult = infoUtentiService.registerUserAuth(email,password);

            createResult.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                        completeRegister(email);
                    else
                        Toast.makeText(getApplicationContext(), "L'indirizzo email e gia in uso", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean controllaEmailEPassword(String email, String password) {
        if (email.length() == 0){
            Toast.makeText(this,"Inserisci un indirizzo email",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (email.length() > 40){
            Toast.makeText(this,"L'indirizzo email non deve superare i 40 caratteri",Toast.LENGTH_SHORT).show();
            return false;
        }

        Pattern pattern = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()){
            Toast.makeText(this,"L'indirizzo email deve rispettare il formato",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.length() == 0){
            Toast.makeText(this,"Inserisci una password",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.length() > 14){
            Toast.makeText(this, "La password non deve superare i 14 caratteri",Toast.LENGTH_SHORT).show();
            return false;
        }

        String ripetiPassword = etRipetiPassword.getText().toString();

        if (password.compareTo(ripetiPassword) != 0){
            Toast.makeText(this,"Le password non corrispondono", Toast.LENGTH_SHORT).show();
            return false;
        }

        Pattern pattern1 = Pattern.compile("(?=.*[!@#$%^&*])(?=.*\\d)(?=.*[A-Z]).{8,}");
        Matcher matcher1 = pattern1.matcher(etPassword.getText().toString());

        if (!matcher1.matches()){
            Toast.makeText(this,"La password deve avere almeno 8 caratteri, di cui uno maiuscolo,un numero, un carattere speciale)",Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
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