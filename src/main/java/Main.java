import model.Status;
import service.ServiceTaskImpl;
import util.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        ServiceTaskImpl serviceTask = new ServiceTaskImpl();
        serviceTask.deleteTask("8878a22b-d526-472c-9a29-db6a9d2bb6d2");
/*        try(BufferedReader in = FileManager.getReader("Task");){
            serviceTask.AddTask("pene","Crear una medalla a pene");
            in.lines().toList().forEach((line)->{
                String []task = line.split(",");
                Arrays.stream(task).toList().forEach(System.out::println);
            });

        }
        catch (Exception ex){
            System.out.println(ex);
        }*/
    }
}
