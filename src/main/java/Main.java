import console.ConsoleCli;
import util.FileManager;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException{
        FileManager.checkFilesExistingIfNotCreated();
        ConsoleCli.inicioConsole();
    }
}
