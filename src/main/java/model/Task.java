package model;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@Builder
public class Task   {
    private String id;
    private String theme;
    private String description;
    @Builder.Default private Status status = Status.TODO;
    @Builder.Default private LocalDateTime created_at = LocalDateTime.now();
    @Builder.Default private LocalDateTime update_at = null;

    public Task(){
        super();
    }
    public Task(String id,String theme,String description,Status status,LocalDateTime created_at,LocalDateTime update_at){
        this.id=id;
        this.theme=theme;
        this.description=description;
        this.status=status;
        this.created_at=created_at;
        this.update_at=update_at;
    }

    public String toStringTask(){
        var coma=",";
        var updated= (update_at == null)? "Sin_editar":update_at.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm"));
        return id+coma+theme+coma+description+coma+status+coma+created_at.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm"))+coma+updated;
    }
}

