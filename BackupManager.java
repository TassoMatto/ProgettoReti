import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * 
 * @class                   BackupManager
 * @brief                   Classe che gestisce dati di backup in formato json
 * @author                  Simone Tassotti
 * @date                    07/03/2023
 * @version                 1.0
 * 
 */

public class BackupManager {

    /** Attributi */
    private final String JsonFilePath;                  // Path del file su cui andare a salvare/recuperare i file di backup

    /**
     * 
     * @fun                             BackupManager
     * @brief                           Metodo costruttore di base 
     * @throws FileNotFoundException
     * 
     */
    public BackupManager() throws FileNotFoundException {
        this.JsonFilePath = "UsersDatabase.json";
        if(Path.of(JsonFilePath).toFile().exists() && !Path.of(JsonFilePath).toFile().isFile()) throw new FileNotFoundException();
    }

    /**
     * 
     * @fun                                 BackupManager
     * @brief                               Metodo costruttore
     * @param JsonFilePath                  Path del file da dove recuperare/aggiornare il contenuto informativo
     * @throws FileNotFoundException
     * @throws IllegalArgumentException
     * 
     */
    public BackupManager(String JsonFilePath) throws FileNotFoundException {
        
        /** Controllo argomenti */
        if(JsonFilePath.equals("")) throw new IllegalArgumentException();
        if(Path.of(JsonFilePath).toFile().exists() && !Path.of(JsonFilePath).toFile().isFile()) throw new FileNotFoundException();

        this.JsonFilePath = JsonFilePath;
    }

    /**
     * 
     * @fun                 infoRecovery
     * @brief               Recupera le informazioni di Utenti dal file json
     * @return              Lista di informazioni sugli utenti, null in caso di errore
     * @throws FileNotFoundException
     * 
     */
    public List<Utente> infoRecovery() throws FileNotFoundException {
        
        /** Tento di aprire il file e valuto la sua esistenza */
        LinkedList<Utente> databaseUtenti = new LinkedList<>();
        File database = new File(JsonFilePath);
        if(!database.exists()) throw new FileNotFoundException();
        if (!database.isFile()) throw new FileNotFoundException();

        /** Estraggo dal file gli utenti memorizzati */
        try {
            JsonFactory factory = new JsonFactory();
            ObjectMapper mapper = new ObjectMapper();
            JsonParser parser = factory.createParser(database);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            parser.setCodec(mapper);
            if(parser.nextToken() != JsonToken.START_ARRAY) {
                return null;
            }
            while ( parser.nextToken() == JsonToken.START_OBJECT ) {
                Utente u = parser.readValueAs(Utente.class);
                databaseUtenti.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return databaseUtenti;
        
    }
    
}
