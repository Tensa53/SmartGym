package com.example.smartgym.infoUtenti.application.logic;

import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * La seguente interfaccia definisce quali funzionalita e servizi bisogna implementare
 */
public interface InfoUtentiService {

    Task<AuthResult> login(String email, String password);

    FirebaseUser isUserLogged();

    void logout ();

    Task<AuthResult> createUser(String email, String password);

    void saveAthlete(Atleta atleta);

    Task<DocumentSnapshot> getAthletebyEmail(String email);

    void editAthleteInfo();

    void insertAthleteFeatures();

    void editAthleteFeatures();

    void deleteUser(String email);

    Task<Void> deleteAthlete(String email);

}
