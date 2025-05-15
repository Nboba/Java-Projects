package com.nboba.BlogPersonal.blog.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@NoArgsConstructor
@Data
@Table(name = "Blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name="fecha_edicion")
    private LocalDateTime fechaEdicion;

    @ManyToMany(mappedBy = "blog", cascade = CascadeType.REMOVE)
    private List<Entry> entry;

}
