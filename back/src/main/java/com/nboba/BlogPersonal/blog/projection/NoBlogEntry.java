package com.nboba.BlogPersonal.blog.projection;

import com.nboba.BlogPersonal.blog.converter.LocalDateTimeAttributeConverter;
import jakarta.persistence.Convert;
import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.convert.ValueConverter;


import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
@Builder
public class NoBlogEntry {

    private final Long id;
    private final String content;

    private final Timestamp fechaCreacion;

    private final Timestamp fechaEdicion;

    private final Long blogId;

    @PersistenceCreator
    public NoBlogEntry(Long id, String content, Timestamp fechaCreacion, Timestamp fechaEdicion, Long blogId) {
        this.id = id;
        this.content = content;
        this.fechaCreacion = fechaCreacion;
        this.blogId=blogId;
        this.fechaEdicion = fechaEdicion !=null? fechaEdicion : null;
    }


}
