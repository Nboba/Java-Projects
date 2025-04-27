package model;


import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
public class Task {
    private String id;
    private String theme;
    private String description;
    @Builder.Default private Status status = Status.TODO;
    @Builder.Default private LocalDateTime created_at = LocalDateTime.now();
    @Builder.Default private LocalDateTime update_at = null;


    public String toStringTask(){
        var coma=",";
        var updated= (update_at == null)? "Sin_editar":update_at.toString();
        return id+coma+theme+coma+description+coma+status+coma+created_at.toString()+coma+updated;
    }
}

