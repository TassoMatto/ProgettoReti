package Server;

import java.rmi.RemoteException;

/**
 * 
 * @interface               UserAuthenticator
 * @brief                   Gestione di servizi di autenticazione di un server da un client remoto
 * @author                  Simone Tassotti
 * @date                    10/03/2023
 * @version                 1.0
 * 
 */

public class UserRMI implements UserAuthenticator { 
    
    /** Variabili statiche */
    private static final String HASH_STRING_CONCAT = "RETI2022_2023";           // Stringa di aggiunta per criptare la password in hashcode
    
    /** Attributi */
    private GameServer gs;             // Server a cui fa riferimento la fase di autenticazione

    public UserRMI(GameServer gs) throws RemoteException {
        this.gs = gs;
    }

    /**
     * 
     * @fun                             registerUser
     * @brief                           Registrazione di un utente su un server
     * @param username                  Username dell'utente
     * @param password                  Password dell'utente
     * @return                          true in caso di avvenuta registrazione, false altrimenti
     * @throws RemoteException
     * 
     */
    @Override
    public boolean registerUser(String username, String password) throws RemoteException {

        /** Controllo argomenti */
        if(username.equals("")) throw new IllegalArgumentException();
        if(password.equals("")) throw new IllegalArgumentException();

        /** Controllo che l'utente non sia presente nel server */
        int hashC = (username + password + HASH_STRING_CONCAT).hashCode();
        Utente u = new Utente(username, Integer.toString(hashC));
        return gs.addUserToDB(u);
    }

    /**
     * 
     * @fun                             loginUser
     * @brief                           Login di un utente su un server
     * @param username                  Username dell'utente
     * @param password                  Password dell'utente
     * @return                          true in caso di avvenuto login, false altrimenti
     * @throws RemoteException
     * 
     */
    @Override
    public boolean loginUser(String username, String password) throws RemoteException {

        /** Controllo argomenti */
        if(username.equals("")) throw new IllegalArgumentException();
        if(password.equals("")) throw new IllegalArgumentException();

        /** Controllo l'esistenza dell'utente */
        int hashC = (username + password + HASH_STRING_CONCAT).hashCode();
        return loginUser(username, Integer.toString(hashC));
    }

    /**
     * 
     * @fun                             logout
     * @brief                           Effettua logout sul server per l'utente
     * @param username                  Username dell'utente
     * @param password                  Password utente
     * @throws RemoteException          
     * 
     */
    @Override
    public void logout(String username, String password) throws RemoteException {
        
    }
}