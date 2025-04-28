package service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;
import model.Status;
import model.Task;
import util.FileManager;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class ServiceTaskImpl implements ServiceTask {
    private static HashMap<String,Task> tasks = new HashMap<>();;
    private FileManager fileManager;

    public ServiceTaskImpl(){
        this.fillTaskMap();
    }
    public Task getTask(String id){
        return tasks.get(id);
    }
    public Set<Task> getTasks(){
        return  tasks.values().stream().collect(Collectors.toUnmodifiableSet());
    }
    @Override
    public Task addTask(String name, String description) throws IOException{
        if(name.length() == 0 || description.length() == 0){
            return null;
        }
        Task task=  Task.builder().
                id(UUID.randomUUID().toString()).
                name(name).
                description(description).
                build();
        tasks.put(task.getId(),task);

        this.saveTasks();
        return task;
    }

    @Override
    public Task updateName(String id, String name) {
        var task = tasks.get(id);
        if(task == null) return null;
        task.setName(name);
        task.setUpdate_at(LocalDateTime.now());
        tasks.put(task.getId(),task);
        try{
            this.saveTasks();
        }
        catch (IOException io){}
        return task;
    }

    @Override
    public Task updateDescription(String id, String description){
        var task = tasks.get(id);
        if(task == null) return null;
        task.setDescription(description);
        task.setUpdate_at(LocalDateTime.now());
        tasks.put(task.getId(),task);
        try{
            this.saveTasks();
        }
        catch (IOException io){}
        return task;
    }

    @Override
    public Task updateTodo(String id, String todo){
        var task = tasks.get(id);
        if(task == null) return null;
        task.setStatus(Status.getStatus(todo.toUpperCase()));
        task.setUpdate_at(LocalDateTime.now());
        tasks.put(task.getId(),task);
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

    public List<Task> getAllTask(){
        return tasks.values().stream().collect(Collectors.toUnmodifiableList());
    }
    private void fillTaskMap(){
        try(JsonParser reader = fileManager.getReader("Task");){
            ArrayList<Task> taks  =reader.readValueAs(new TypeReference<ArrayList<Task>>(){});
            taks.forEach((tak)->{
                tasks.put(tak.getId(),tak);
            });
            }
        catch (IOException io){}
    }


    private void saveTasks() throws IOException{
        try(var file =fileManager.getWriter("Task");) {
            file.writePOJO(tasks.values().toArray());
        }
    }
}
