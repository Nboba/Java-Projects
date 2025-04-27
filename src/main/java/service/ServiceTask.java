package service;

import model.Task;

import java.io.IOException;
import java.util.List;

public interface ServiceTask {
    Task AddTask(String theme, String description) throws IOException;


    Task updateTheme(String id, String theme);

    Task updateDescription(String id, String description);

    Task updateTodo(String id, String todo);

    void deleteTask(String id);

    List<Task> getFilteredListByTodo(String todo);

    /*    @Override
                                                public Task updateTask(String id,String description){

                                                }*/
    void fillTaskMap();

    void saveTasks() throws IOException;
}
