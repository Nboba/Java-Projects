package com.nboba.BlogPersonal.blog.service;

import com.nboba.BlogPersonal.blog.model.Blog;
import com.nboba.BlogPersonal.blog.projection.NoEntrysBlog;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.NoSuchElementException;

public interface BlogService {

    NoEntrysBlog createBlog(Blog blog);

    Boolean deleteBlog(Long blogId);

    NoEntrysBlog updateTitle(Long blogId, Blog newTitle);

    List<NoEntrysBlog> getBlogs() ;
}
