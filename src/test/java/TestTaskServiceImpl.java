import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import model.Status;
import model.Task;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import service.ServiceTaskImpl;
import util.FileManager;

import java.io.IOException;


public class TestTaskServiceImpl {
    public static final JsonParser reader = FileManager.getReader("Task");
    public static JsonGenerator writer = FileManager.getWriter("Task");
    public static ServiceTaskImpl serviceTask = new ServiceTaskImpl();
    public String theme= "News";
    public String description = "Nuevo mundo de las destruccion";


    @Test
    public void testAddTaskCorrect() throws IOException {
        var task = serviceTask.addTask(theme,description);
        assertEquals(theme,task.getName());
        assertEquals(description,task.getDescription());
        serviceTask.deleteTask(task.getId());
    }
    @Test
    public void testUpdateName() throws IOException{
        String newName="Olds";
        var task = serviceTask.addTask(theme,description);
        assertEquals(newName,serviceTask.updateName(task.getId(),newName).getName());
        serviceTask.deleteTask(task.getId());
    }
    @Test
    public void testUpdateDescription() throws IOException{
        String newDescription="Viejo mundo de la creacion";
        var task = serviceTask.addTask(theme,description);
        assertEquals(newDescription,serviceTask.updateDescription(task.getId(),newDescription).getDescription());
        serviceTask.deleteTask(task.getId());
    }
    @Test
    public void testUpdateTodo() throws IOException{
        Status newStatus=Status.ABANDONED;
        var task = serviceTask.addTask(theme,description);
        assertEquals(newStatus,serviceTask.updateTodo(task.getId(),newStatus.name()).getStatus());
        serviceTask.deleteTask(task.getId());
    }
    @Test
    public void testDeleteTask() throws IOException{
        var task = serviceTask.addTask(theme,description);
        serviceTask.deleteTask(task.getId());
        assertFalse(serviceTask.getTasks().contains(task));
    }
}
