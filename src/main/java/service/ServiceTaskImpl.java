package service;

import lombok.Data;
import model.Status;
import model.Task;
import util.FileManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class ServiceTaskImpl implements ServiceTask {
    public static HashMap<String,Task> tasks = new HashMap<>();;
    private FileManager fileManager;

    public ServiceTaskImpl(){
        this.fillTaskMap();
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
        tasks.put(task.getId(),task);

        this.saveTasks();
        return task;
    }

    @Override
    public Task updateTheme(String id, String theme) {
        var task = tasks.get(id);
        task.setTheme(theme);
        task.setUpdate_at(LocalDateTime.now());
        try{
            this.saveTasks();
        }
        catch (IOException io){}
        return task;
    }

    @Override
    public Task updateDescription(String id, String description){
        var task = tasks.get(id);
        task.setDescription(description);
        task.setUpdate_at(LocalDateTime.now());
        try{
            this.saveTasks();
        }
        catch (IOException io){}
        return task;
    }

    @Override
    public Task updateTodo(String id, String todo){
        var task = tasks.get(id);
        task.setStatus(Status.getStatus(todo.toUpperCase()));
        task.setUpdate_at(LocalDateTime.now());
        try{
            this.saveTasks();
        }
        catch (IOException io){}
        return task;
    }

    @Override
    public void deleteTask(String id){
        tasks.remove(id);
        try{
            this.saveTasks();
        }
        catch (IOException io){}
    }

    @Override
    public List<Task> getFilteredListByTodo(String todo){
        return tasks.values().stream().filter((task)->  { return
                task.getStatus().name().compareTo(todo.toUpperCase()) == 0;
        }).collect(Collectors.toUnmodifiableList());
    }
    @Override
    public void fillTaskMap(){
        try(BufferedReader reader = fileManager.getReader("Task");){
            reader.lines().toList().forEach((line)->{
                String []taskFiles = line.split(",");
                Task task;
                task= Task.builder().
                        id(taskFiles[0]).
                        theme(taskFiles[1]).
                        description(taskFiles[2]).
                        status(Status.getStatus(taskFiles[3])).
                        created_at(LocalDateTime.parse(taskFiles[4])).
                        build();
                task.setUpdate_at((taskFiles[5].compareTo("Sin_editar") ==0) ? null: LocalDateTime.parse(taskFiles[5]));
                tasks.put(task.getId(),task);
            });
            }
        catch (IOException io){}
    }

    @Override
    public void saveTasks() throws IOException{

        try(var file =fileManager.getWriter("Task");) {
            tasks.values().forEach((tas)->{
                try{
                    file.write(tas.toStringTask()+"\n");}
                catch (IOException io){}
            });

        }

    }
}
