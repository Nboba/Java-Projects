import service.ServiceTaskImpl;
import util.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        ServiceTaskImpl serviceTask = new ServiceTaskImpl();
        System.out.println(ServiceTaskImpl.tasks.toString());
/*        try(BufferedReader in = FileManager.getReader("Task");){
            //serviceTask.AddTask("marshal","Crear una medalla");
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
