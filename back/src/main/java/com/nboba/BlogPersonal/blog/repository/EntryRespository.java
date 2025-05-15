package com.nboba.BlogPersonal.blog.repository;

import com.nboba.BlogPersonal.blog.model.Entry;
import com.nboba.BlogPersonal.blog.projection.NoBlogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface EntryRespository extends JpaRepository<Entry,Long> {
    Optional<Entry> findById(Long id);

    //@Query(value="SELECT NEW com.nboba.BlogPersonal.blog.projection.NoBlogEntry(e.id, e.content, e.fechaCreacion, e.fechaModificacion, e.blog) from Entry e WHERE e.blog_id= :blogId")
    @Query(value="" +
            "SELECT e.id, e.content, e.fecha_creacion, e.fecha_edicion,e.blog_id " +
            "from sc_blog.Blog_entry e " +
            "WHERE e.blog_id = :blogId " +
            "ORDER BY e.fecha_creacion DESC",nativeQuery = true)
    List<NoBlogEntry> findsAllNotBlog(@Param("blogId") Long blogId);


}
