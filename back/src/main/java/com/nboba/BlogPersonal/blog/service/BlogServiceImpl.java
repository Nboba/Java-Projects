package com.nboba.BlogPersonal.blog.service;

import com.nboba.BlogPersonal.blog.model.Blog;
import com.nboba.BlogPersonal.blog.projection.NoEntrysBlog;
import com.nboba.BlogPersonal.blog.repository.BlogRespository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{

    private BlogRespository blogRespository;

    public BlogServiceImpl(BlogRespository blogRespository){
        this.blogRespository=blogRespository;
    }

    @Override
    public NoEntrysBlog createBlog(Blog blog){
        blog.setFechaCreacion(LocalDateTime.now());
        blog =blogRespository.save(blog);
        return NoEntrysBlog.builder().
                id(blog.getId()).
                title(blog.getTitle()).
                fechaCreacion(blog.getFechaCreacion()).
                fechaEdicion(blog.getFechaEdicion()).
                build();
    }

    @Override
    public Boolean deleteBlog(Long blogId){
        Blog blog= blogRespository.getReferenceById(blogId);
        blogRespository.delete(blog);
        return true;
    }

    @Override
    public NoEntrysBlog updateTitle(Long blogId, Blog newTitle){
        Blog blog= blogRespository.getReferenceById(blogId);
        blog.setTitle(newTitle.getTitle());
        blog.setFechaEdicion(LocalDateTime.now());
        blog =blogRespository.save(blog);
        return NoEntrysBlog.builder().
                id(blog.getId()).
                title(blog.getTitle()).
                fechaCreacion(blog.getFechaCreacion()).
                fechaEdicion(blog.getFechaEdicion()).
                build();
    }

    @Override
    public List<NoEntrysBlog> getBlogs(){
        List<NoEntrysBlog> blogs = blogRespository.findsAllNotEntry();
        return blogs;
    }

    public Blog getBlogById(Long blogId) {
        if(blogRespository.findById(blogId).isPresent()) return blogRespository.findById(blogId).get();
        return null;
    }

}
