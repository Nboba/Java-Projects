package service;

import lombok.Data;
import model.Status;
import model.Task;
import util.FileManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class ServiceTaskImpl implements ServiceTask {
    public static HashMap<String,Task> tasks = new HashMap<>();;
    private FileManager fileManager;

    public ServiceTaskImpl(){

        try(BufferedReader reader = fileManager.getReader("Task");){
            reader.lines().toList().forEach((line)->{
                String []taskFiles = line.split(",");
                Task task;
                task= Task.builder().
                        id(taskFiles[0]).
                        theme(taskFiles[1]).
                        description(taskFiles[2]).
                        status(Status.valueOf(taskFiles[3])).
                        created_at(LocalDateTime.parse(taskFiles[4])).
                        build();
                task.setUpdate_at((taskFiles[5].compareTo("Sin_editar") ==0) ? null: LocalDateTime.parse(taskFiles[5]));
                tasks.put(task.getId(),task);
            });
        }
        catch (IOException io){}
    }
    @Override
    public Task AddTask(String theme, String description) throws IOException{
        if(theme.length() == 0 || description.length() == 0){
            return null;
        }
        Task task=  Task.builder().
                id(UUID.randomUUID().toString()).
                theme(theme).
                description(description).
                build();
        //Tasks.put(task.getId(),task);

        try(var file =fileManager.getWriter("Task");) {

            System.out.println(task.toStringTask());
            file.write(task.toStringTask());
            file.close();
        }catch (IOException io){
            System.out.println("Task Service Exception: "+io);
        }
        return task;
    }

/*    @Override
    public Task updateTask(String id,String description){

    }*/
}
