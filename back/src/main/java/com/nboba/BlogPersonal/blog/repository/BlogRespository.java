package com.nboba.BlogPersonal.blog.repository;

import com.nboba.BlogPersonal.blog.model.Blog;
import com.nboba.BlogPersonal.blog.projection.NoEntrysBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BlogRespository extends JpaRepository<Blog,Long > {

    @Query(value="SELECT NEW com.nboba.BlogPersonal.blog.projection.NoEntrysBlog(b.id,b.title,b.fechaCreacion,b.fechaEdicion) from Blog b ORDER BY b.fechaCreacion DESC")
    List<NoEntrysBlog> findsAllNotEntry();
}
