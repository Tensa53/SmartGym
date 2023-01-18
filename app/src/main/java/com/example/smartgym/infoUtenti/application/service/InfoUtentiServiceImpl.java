package com.example.smartgym.infoUtenti.application.service;
import com.example.smartgym.infoUtenti.application.service.InfoUtentiService;
import com.example.smartgym.infoUtenti.storage.dataAccess.UtenteDAO;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class InfoUtentiServiceImpl implements InfoUtentiService {


    /**
     * effettua il login dell'utente nel sistema
     *
     * @param email l'indirizzo email associato all'utente
     * @param password la password corrispondente
     * @return il riferimento al task di login per verificarne il successo o il fallimento
     */
    @Override
    public Task<AuthResult> login(String email, String password) {
        FirebaseAuth mauth = FirebaseAuth.getInstance();

        Task<AuthResult> task = mauth.signInWithEmailAndPassword(email, password);

        return task;
    }


    /**
     * effettua il logout dell'utente dal sistema
     */
    @Override
    public void logout() {
        FirebaseAuth mauth = FirebaseAuth.getInstance();

        mauth.signOut();

        return;
    }

    /**
     * accede al DAO per permettere di recuperare i dati dell'utente
     *
     * @param id l'identificativo del documento dell'utente salvato nel db
     * @return il riferimento all'operazione di query per verificarne il successo e recuperare i dati dell'utente
     */
    @Override
    public Task<DocumentSnapshot> getUserbyId(String id) {
        UtenteDAO utenteDAO = new UtenteDAO();

        Task<DocumentSnapshot> task = utenteDAO.doRetrieveUserDocById(id);

        return task;
    }

    /**
     *  accede al DAO per permettere di recuperare i dati dell'utente
     *
     * @param email l'indirizzo email associato all'utente
     * @return il riferimento all'operazione di query per verificarne il successo e recuperare i dati dell'utente
     */
    @Override
    public Task<QuerySnapshot> getUserbyEmail(String email) {
        UtenteDAO utenteDAO = new UtenteDAO();

        Task<QuerySnapshot> task = utenteDAO.doRetrieveUserDocByEmail(email);

        return task;
    }
}
