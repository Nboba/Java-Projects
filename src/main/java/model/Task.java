package model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@Builder
public class Task implements Comparable {
    private String id;
    private String name;
    private String description;
    @Builder.Default private Status status = Status.TODO;
    @Builder.Default private LocalDateTime created_at = LocalDateTime.now();
    @Builder.Default private LocalDateTime update_at = null;

    public Task(){
        super();
    }
    public Task(String id,String theme,String description,Status status,LocalDateTime created_at,LocalDateTime update_at){
        this.id=id;
        this.name=theme;
        this.description=description;
        this.status=status;
        this.created_at=created_at;
        this.update_at=update_at;
    }

    public String toStringTask(){
        var coma=",";
        var updated= (update_at == null)? "Sin_editar":update_at.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm"));
        return id+coma+name+coma+description+coma+status+coma+created_at.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm"))+coma+updated;
    }

    public void printTodo(){
        var updated= (update_at == null)? "Sin editar":update_at.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm"));
        System.out.printf("""
                                    Tarea: %s , con estado: %s  
                                    Fecha de creacion: %s y Fecha de actualizacion: %s
                                    Descripcion: %s \n
                            """,
                name,
                status.name(),
                created_at.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm")),
                updated,
                description);
    }
    public void shortPrint(){
        var updated= (update_at == null)? "Sin editar":update_at.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm"));
        System.out.printf("""
                                    Tarea: %s , con estado: %s  
                                    Fecha de creacion: %s y Fecha de actualizacion: %s
                                    Descripcion: %s \n
                            """,
                name,
                status.name(),
                created_at.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm")),
                updated,
                description);
    }
    public void showPrint(int index){
        System.out.printf("             -[ID]:%d [TAREA]: %s [ESTADO]: %s\n", index, name, status);
    }

    @Override
    public int compareTo(Object o) {
        Task local= (Task)o;
        return -this.getCreated_at().compareTo(local.getCreated_at());
    }
}

