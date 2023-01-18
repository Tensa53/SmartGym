package com.example.smartgym.infoUtenti.application.service;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public interface InfoUtentiService {

    Task<AuthResult> login(String email, String password);

    void logout ();

    Task<DocumentSnapshot> getUserbyId(String id);

    Task<QuerySnapshot> getUserbyEmail(String email);

}
