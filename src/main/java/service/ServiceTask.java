package service;

import model.Task;

import java.io.IOException;
import java.util.List;

public interface ServiceTask {
    Task addTask(String name, String description) throws IOException;


    Task updateName(String id, String name);

    Task updateDescription(String id, String description);

    Task updateTodo(String id, String todo);

    void deleteTask(String id);

    List<Task> getFilteredListByTodo(String todo);

}
