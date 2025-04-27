package service;

import model.Task;

import java.io.IOException;

public interface ServiceTask {
    Task AddTask(String theme, String description) throws IOException;
}
