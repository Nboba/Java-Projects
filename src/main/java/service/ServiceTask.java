package service;

import model.Task;

import java.io.IOException;
import java.util.List;

public interface ServiceTask {
    Task addTask(String theme, String description) throws IOException;


    Task updateTheme(String id, String theme);

    Task updateDescription(String id, String description);

    Task updateTodo(String id, String todo);

    void deleteTask(String id);

    List<Task> getFilteredListByTodo(String todo);

    List<Task> getFilteredListByTheme(String theme);

    List<Task> getFilteredListByThemeAndTodo(String theme, String todo);
}
