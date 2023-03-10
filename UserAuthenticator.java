package Server;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @interface               UserAuthenticator
 * @brief                   Interfaccia per la gestione di servizi di autenticazione di un server da un client remoto
 * @author                  Simone Tassotti
 * @date                    07/03/2023
 * @version                 1.0
 * 
 */

public interface UserAuthenticator extends Remote {
    
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
    public boolean registerUser(String username, String password) throws RemoteException;
    
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
    public boolean loginUser(String username, String password) throws RemoteException;

    /**
     * 
     * @fun                             logout
     * @brief                           Effettua logout sul server per l'utente
     * @param username                  Username dell'utente
     * @param password                  Password utente
     * @throws RemoteException          
     * 
     */
    public void logout(String username, String password) throws RemoteException;
}
