package util;

import lombok.Data;
import model.Task;
import model.Theme;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.server.ExportException;
import java.util.Arrays;
import java.util.Scanner;

@Data
public abstract class FileManager implements AutoCloseable {
    private static final String cliName="TaskCli";
    private static final String userPath = System.getProperties().getProperty("user.home")+"\\Documents\\"+cliName;
    private static final String taskPath = userPath + "\\tasks.txt";
    private static final String themePath = userPath + "\\theme.txt";;

    public static void checkFilesExistingIfNotCreated(){
        try{
           for(var path: new String[]{userPath,taskPath,themePath}){
               File file = new File(path);
               if(!file.exists()) {
                   if(userPath.compareTo(path) == 0) {
                       System.out.println("Carpeta "+cliName +" creada");
                       Files.createDirectory(file.toPath());
                   }
                   else{
                       System.out.printf("Archivo creado: %s \n",path);
                       Files.createFile(file.toPath());
                   }
               }
            }
//            System.out.println("Configuracion Exitosa");
        }catch (Exception ex){
            System.out.println(ex);

        }
    }

    public static FileWriter getWriter(String fileName) {
        try {
            File file = switch (fileName.toLowerCase()){
                case "task"-> new File(taskPath);
                default -> new File(themePath);
            };
            FileWriter writer = new FileWriter(file);
            return writer;
        } catch (Exception ex) {
            System.out.println("writer exception "+ex);
            return null;
        }
    }
    public static BufferedReader getReader(String fileName) {
        try {
            File file = switch (fileName){
                case "Task"-> new File(taskPath);
                default -> new File(themePath);
            };
            FileReader reader = new FileReader(file);
            return new BufferedReader(reader);
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

}
