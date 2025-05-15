package com.nboba.BlogPersonal.blog.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Blog_entry")
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private  Blog blog;

    @Column(name = "content")
    private  String content;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_edicion")
    private LocalDateTime fechaEdicion;

    public void setNullBlog(){
        this.blog=null;
    }
}
