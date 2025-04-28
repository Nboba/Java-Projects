package serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import model.Task;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class TaskSerializer extends StdSerializer<Task> {

    public TaskSerializer() {
        this(null);
    }
    protected TaskSerializer(Class<Task> t) {
        super(t);
    }

    @Override
    public void serialize(Task task, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        var updated= (task.getUpdate_at() == null)? "Sin_editar":task.getUpdate_at().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm"));
        jsonGenerator.writeStartObject();
        jsonGenerator.writeString(task.getId());
        jsonGenerator.writeString(task.getName());
        jsonGenerator.writeString(task.getDescription());
        jsonGenerator.writeString(task.getStatus().name());
        jsonGenerator.writeString(task.getCreated_at().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm")));
        jsonGenerator.writeString(updated);
    }
}
