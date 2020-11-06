package com.agamitech.bloggingapp.controller;


import com.agamitech.bloggingapp.dto.ReactionDao;
import com.agamitech.bloggingapp.response.Response;
import com.agamitech.bloggingapp.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @PostMapping("/reaction")
    public ResponseEntity<Response> addReaction(@RequestBody ReactionDao reactionDao) {
        return reactionService.addReaction(reactionDao);
    }

    @GetMapping("likes/{blogId}")
    public ResponseEntity<Integer> getLikes(@PathVariable("blogId") int blogId) {
        return new ResponseEntity<>(reactionService.getLikesByBlogId(blogId), HttpStatus.OK);
    }

    @GetMapping("dislikes/{blogId}")
    public ResponseEntity<Integer> getDisLikes(@PathVariable("blogId") int blogId) {
        return new ResponseEntity<>(reactionService.getDisLikesByBlogId(blogId), HttpStatus.OK);
    }


}
