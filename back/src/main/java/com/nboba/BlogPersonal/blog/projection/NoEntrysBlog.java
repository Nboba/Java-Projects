package com.nboba.BlogPersonal.blog.projection;



import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;

@Data
@Builder
public class NoEntrysBlog  {
    private final Long id;
    private final String title;
    private final LocalDateTime fechaCreacion;
    private final LocalDateTime fechaEdicion;

    @PersistenceCreator
    public NoEntrysBlog(Long id ,String title,LocalDateTime fechaCreacion,LocalDateTime fechaEdicion){
        this.id=id;
        this.title=title;
        this.fechaCreacion=fechaCreacion;
        this.fechaEdicion=fechaEdicion;
    }
}
