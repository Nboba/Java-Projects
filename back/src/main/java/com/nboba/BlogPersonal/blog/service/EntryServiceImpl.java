package com.nboba.BlogPersonal.blog.service;

import com.nboba.BlogPersonal.blog.model.Blog;
import com.nboba.BlogPersonal.blog.model.Entry;
import com.nboba.BlogPersonal.blog.projection.NoBlogEntry;
import com.nboba.BlogPersonal.blog.repository.EntryRespository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EntryServiceImpl implements EntryService{

    private EntryRespository entryRespository;
    private BlogServiceImpl blogService;

    public EntryServiceImpl(EntryRespository repo, BlogServiceImpl blogService){
        this.entryRespository=repo;
        this.blogService=blogService;
    }

    @Override
    public NoBlogEntry saveEntry(Long blogId,Entry entry){
        Blog blog = blogService.getBlogById(blogId);
        if(blog !=null){
            entry.setFechaCreacion(LocalDateTime.now());
            entry.setBlog(blog);
            Entry localEntry=entryRespository.save(entry);
            return NoBlogEntry.builder().
                    id(localEntry.getId()).
                    content(entry.getContent()).
                    fechaCreacion(Timestamp.valueOf(entry.getFechaCreacion())).
                    fechaEdicion(null).
                    blogId(blog.getId()).build();
        }
        return null;
    }

    @Override
    public NoBlogEntry updateEntry(Long entryId, Entry content){
        Entry entry = entryRespository.getReferenceById(entryId);
        entry.setContent(content.getContent());
        entry.setFechaEdicion(LocalDateTime.now());
        entry= entryRespository.save(entry);
        return NoBlogEntry.builder().
                id(entry.getId()).
                content(entry.getContent()).
                fechaCreacion(Timestamp.valueOf(entry.getFechaCreacion())).
                fechaEdicion(Timestamp.valueOf(LocalDateTime.now())).
                blogId(entry.getBlog().getId()).build();
    }

    @Override
    public Boolean deleteEntry(Long blogId,Long id){
        Blog blog= blogService.getBlogById(blogId);
        Entry entry = entryRespository.findById(id).orElse(null);
         if(entry !=null &&
            blog != null && blog.getId() == entry.getBlog().getId()) {
             entryRespository.deleteById(id);
             return true;
         };
             return false;
    }

    @Override
    public List<NoBlogEntry> getAllEntrys(Long id){
        Blog blog = blogService.getBlogById(id);
        if(blog !=null){
            List<NoBlogEntry> entrys= entryRespository.findsAllNotBlog(id);
            return entrys;
        }
        return null;
    }

}
