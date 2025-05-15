package com.nboba.BlogPersonal.blog.restController;


import com.nboba.BlogPersonal.blog.model.Blog;
import com.nboba.BlogPersonal.blog.service.BlogServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/blog")
public class BlogRestController {
    private BlogServiceImpl blogService;

    public BlogRestController(BlogServiceImpl blogService){
        this.blogService=blogService;
    }

    @PostMapping()
    public ResponseEntity<?> createBlog(@RequestBody Blog blog){
        return new ResponseEntity(blogService.createBlog(blog), HttpStatus.CREATED);
    }

    @PatchMapping("{blogId}")
    public ResponseEntity<?> updateBlogTitle(@PathVariable("blogId") Long blogId,
                                             @RequestBody Blog title){
        return new ResponseEntity<>(blogService.updateTitle(blogId,title),HttpStatus.OK);
    }
    @DeleteMapping("{blogId}")
    public ResponseEntity<?> deleteBlog(@PathVariable("blogId") Long blogId){
        Boolean isDeleted = blogService.deleteBlog(blogId);
        if(isDeleted) return  new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping()
    public ResponseEntity<?> getBlogs(){
        return new ResponseEntity(blogService.getBlogs(),HttpStatus.OK);
    }

}
