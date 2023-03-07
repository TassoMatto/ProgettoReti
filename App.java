import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Utente prova = new Utente("pippo", "1234");

        System.out.println(prova.getUsername());
        prova.addNewGamePlayed();
        System.out.println(prova.addAttempt("Ciaoooo"));
        System.out.println(prova.addAttempt("aaaaaa"));
        prova.gameWin();
        ArrayList<String> t = prova.lastGameAttempts();
        for (String string : t) {
            System.out.println(string);
        }
    }
}
