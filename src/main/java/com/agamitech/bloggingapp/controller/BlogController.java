package com.agamitech.bloggingapp.controller;


import com.agamitech.bloggingapp.dto.BlogDto;
import com.agamitech.bloggingapp.dto.UpdateBlogDto;
import com.agamitech.bloggingapp.model.Blog;
import com.agamitech.bloggingapp.response.Response;
import com.agamitech.bloggingapp.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/blog")
    public ResponseEntity<Response> createBlog(@RequestBody BlogDto blogDto) {
        return blogService.createPost(blogDto);
    }

    @PutMapping("/blog")
    public ResponseEntity<Response> updateBlog(@RequestBody UpdateBlogDto updateBlogDto) {
        return blogService.updateBlog(updateBlogDto);
    }

    @DeleteMapping("/blog/{blogId}")
    public ResponseEntity<String> deleteBlog(@PathVariable("blogId") int blogId) {
        return new ResponseEntity<>(blogService.deleteBlog(blogId), HttpStatus.CREATED);
    }

    @GetMapping("/blog")
    public ResponseEntity<List<Blog>> getBlog() {
        return blogService.getBlog();
    }


}