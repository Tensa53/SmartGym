package com.example.smartgym.infoUtenti.application.logic;

import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

/**
 * La seguente interfaccia definisce quali funzionalita e servizi bisogna implementare
 */
public interface InfoUtentiService {

    Task<AuthResult> login(String email, String password);

    FirebaseUser getUserLogged();

    void logout ();

    Task<AuthResult> createUser(String email, String password);

    Task<Void> saveAthlete(Atleta atleta, String id);

    Task<DocumentSnapshot> getAthletebyId(String id);

    Task<Void> editAthleteInfo(Atleta atleta, String id);

    Task<Void> insertAthleteFeatures(Atleta atleta, String id);

    Task<Void> editAthleteFeatures(Atleta atleta, String id);///

    Task<Void> deleteUser();///

    Task<Void> deleteAthlete(String id);///

}
