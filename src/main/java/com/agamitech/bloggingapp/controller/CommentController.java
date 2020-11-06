package com.agamitech.bloggingapp.controller;


import com.agamitech.bloggingapp.dto.CommentDao;
import com.agamitech.bloggingapp.model.Comment;
import com.agamitech.bloggingapp.response.Response;
import com.agamitech.bloggingapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping("/comment")
    public ResponseEntity<Response> createBlog(@RequestBody CommentDao commentDao) {
        return commentService.addComment(commentDao);
    }

    @GetMapping("/comment/{blogId}")
    public ResponseEntity<List<Comment>> getComment(@PathVariable("blogId") int blogId) {
        return commentService.getComment(blogId);
    }


}
