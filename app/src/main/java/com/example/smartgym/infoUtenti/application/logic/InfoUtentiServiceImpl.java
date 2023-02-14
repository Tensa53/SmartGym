package com.example.smartgym.infoUtenti.application.logic;
import com.example.smartgym.infoUtenti.storage.entity.Atleta;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

/**
 * La seguente classe fornisce i servizi e le funzionalita per il package (sottosistema) InfoUtenti
 */
public class InfoUtentiServiceImpl implements InfoUtentiService {

    LoginRegistration loginRegistration;
    AthleteInfo athleteInfo;

    public InfoUtentiServiceImpl(){
        loginRegistration = new LoginRegistration();
        athleteInfo = new AthleteInfo();
    }

    /**
     * effettua il login dell'utente nel sistema
     *
     * @param email l'indirizzo email associato all'utente
     * @param password la password corrispondente
     * @return il riferimento al task di login per verificarne il successo o il fallimento
     */
    @Override
    public Task<AuthResult> login(String email, String password) {
        return loginRegistration.login(email,password);
    }

    /**
     * verifica se l'utente ha effettuato il login
     *
     * @pre login(String email, String password)
     * @return l'utente attualmente loggato nel sistema
     */
    public FirebaseUser getUserLogged(){
        return loginRegistration.getUserLogged();
    }

    /**
     * effettua il logout dell'utente dal sistema
     */
    @Override
    public void logout() {
        loginRegistration.logout();
    }

    /**
     * crea il record dell'utente per il servizio di autenticazione
     *
     * @param email l'indirizzo email associato all'utente
     * @param password la password corrispondente
     * @return il riferimento all'operazione di creazione autenticazione per verificarne il successo o il fallimento
     */
    @Override
    public Task<AuthResult> createUser(String email, String password) {
        return loginRegistration.createUser(email,password);
    }

    /**
     * salva i dati dell'utente atleta nel database
     *
     * @param atleta istanza della classe Atleta, contiene i dati relativi all'utente atleta
     */
    public Task<Void> saveAthlete(Atleta atleta, String id){
        return athleteInfo.saveAthlete(atleta,id);
    }

    /**
     *  accede al DAO per permettere di recuperare i dati dell'utente
     *
     * @param id l'id di registrazione del profilo
     * @return il riferimento al documento per prelevare i dati
     */
    @Override
    public Task<DocumentSnapshot> getAthletebyId(String id) {
        return athleteInfo.getAthletebyId(id);
    }

    @Override
    public Task<Void> editAthleteInfo(Atleta atleta, String id) {
        return athleteInfo.editAthleteInfo(atleta, id);
    }


    @Override
    public Task<Void> insertAthleteFeatures(Atleta atleta, String id) {
        return  athleteInfo.insertAthleteFeatures(atleta, id);
    }

    @Override
    public Task<Void> editAthleteFeatures(Atleta atleta, String id) {
        return athleteInfo.editAthleteFeatures(atleta, id);
    }

    @Override
    public Task<Void> deleteUser() {
        return loginRegistration.deleteUser();
    }

    @Override
    public Task<Void> deleteAthlete(String id) {
        return athleteInfo.deleteAthlete(id);
    }

}
