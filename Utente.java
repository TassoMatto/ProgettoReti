import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 
 * @class               Utente
 * @brief               Classe di gestione delle statistiche, raking di gioco di un utente della piattaforma
 * @author              Simone Tassotti
 * @date                06/03/2023
 * @version             1.0
 * 
 */

public class Utente implements Serializable {
    
    /** Variabili globali */
    private static final int MAX_ATTEMPTS = 12;

    /** Attributi Utente */
    private final String username;                          // Username dell'utente
    private final String password;                          // Password utente
    private int gamePlayed;                                 // Numero di partite giocate
    private LinkedList<Boolean> gamesWon;                   // Lista che indica i game vinti dall'utente
    private int successGameRow;                             // Ultima fila di risultati utili consecutivi
    private int bestSuccessGameRow;                         // Migliore file di risultati utili consecutivi
    private LinkedList<ArrayList<String>> attemptString;    // Lista di tutti i tentativi per ogni partita giocata

    /**
     * 
     * @fun                         Utente
     * @brief                       Metodo costruttore
     * @param username              Username dell'utente
     * @param password              Password dell'utente
     * @throws                      IllegalArgumentException
     * 
     */
    public Utente(String username, String password) {
 
        super();    // Uso per serializzazzione

        if((password.equals("")) || (username.equals(""))) throw new IllegalArgumentException();

        this.username = username;
        this.password = password;
        this.gamePlayed = 0;
        this.gamesWon = new LinkedList<>();
        this.successGameRow = 0;
        this.bestSuccessGameRow = 0;
        this.attemptString = new LinkedList<>();
    }

    /**
     * 
     * @fun                         getUsername
     * @brief                       Restuisce l'username dell'utente
     * @return                      Username dell'utente
     * 
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @fun                 addAttempt
     * @brief               Aggiungo una stringa di suggerimenti che poi l'utente può condividere 
     * @param suggestions   Tentativo per una parola del dizionario, corrispondenti ad un tentativo immesso nel sistema
     * @throws              IllegalArgumentException
     * @return              true, ho aggiunto un suggerimento, false altrimenti
     * 
     */
    public boolean addAttempt(String attempt) {

        /** Controllo argomenti */
        if(attempt.equals("")) throw new IllegalArgumentException();
        if((this.attemptString.size() == 0) || (this.attemptString.getLast().size() == MAX_ATTEMPTS)) return false;
        
        this.attemptString.getLast().add(attempt);

        return true;

    }

    /**
     * 
     * @fun                     addNewGamePlayed
     * @brief                   L'utente ha partecipato ad un nuovo gioco
     * 
     */
    public void addNewGamePlayed() {
        this.gamePlayed++;
        this.attemptString.add(new ArrayList<>());
        this.gamesWon.add(false);
    }

    /**
     * 
     * @fun                     gameWin
     * @brief                   Dichiara vinto l'ultimo gioco a cui a partecipato l'utente
     * 
     */
    public void gameWin() {
        this.gamesWon.set(this.gamePlayed-1, true);
        if(this.gamesWon.size() == 1) this.successGameRow = 1;
        else if(this.gamesWon.get(this.gamePlayed-2)) this.successGameRow++;

        this.bestSuccessGameRow = (this.successGameRow > this.bestSuccessGameRow) ? this.successGameRow : this.bestSuccessGameRow;
    }

    /**
     * 
     * @fun                 lastGameAttempts
     * @brief               Ritorna la lista di tentativi effettuati dall'utente per cercare di indovinare l'ultima parola
     * @return              In caso di successo ritorna la lista di tentativi effettuati dall'utente, null in caso di nessun
     *                      tentativo effettuato o in caso di errore
     * 
     */
    public ArrayList<String> lastGameAttempts() {
        ArrayList<String> tmp = null;

        if(this.attemptString.size() != 0) tmp = new ArrayList<>(this.attemptString.getLast());

        return tmp;
    }
}
