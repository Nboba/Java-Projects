package util;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Data;

import java.io.*;
import java.nio.file.Files;

@Data
public abstract class FileManager implements AutoCloseable {
    private static final String cliName="TaskCli";
    private static final String userPath = System.getProperties().getProperty("user.home")+"\\Documents\\"+cliName;
    private static final String taskPath = userPath + "\\tasks.json";
    private static final String themePath = userPath + "\\theme.json";;
    private static ObjectMapper mapper =getMapper();
                                            ;
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

    public static JsonGenerator getWriter(String fileName) {
        try {
            File file = switch (fileName.toLowerCase()){
                case "task"-> new File(taskPath);
                default -> new File(themePath);
            };
            JsonGenerator writer =  mapper.createGenerator(FileManager.getTaskFile(), JsonEncoding.UTF8);
            return writer;
        } catch (Exception ex) {
            System.out.println("writer exception "+ex);
            return null;
        }
    }
    public static JsonParser getReader(String fileName) {
        try {
            File file = switch (fileName){
                case "Task"-> new File(taskPath);
                default -> new File(themePath);
            };
            FileReader reader = new FileReader(file);
            return mapper.createParser(FileManager.getTaskFile());
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static File getTaskFile(){
        return new File(taskPath);
    }
    public static File getThemeFile(){
        return new File(themePath);
    }
    private static ObjectMapper getMapper() {
        mapper = new ObjectMapper().
                disable(MapperFeature.REQUIRE_HANDLERS_FOR_JAVA8_TIMES).
                disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .registerModule(new JavaTimeModule());
        SimpleModule module = new SimpleModule();
        mapper.registerModule(module);
        return mapper;
    }
}
