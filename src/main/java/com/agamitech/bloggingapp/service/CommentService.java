package com.agamitech.bloggingapp.service;


import com.agamitech.bloggingapp.constant.Constants;
import com.agamitech.bloggingapp.dto.CommentDao;
import com.agamitech.bloggingapp.model.Comment;
import com.agamitech.bloggingapp.repository.AuthorRepository;
import com.agamitech.bloggingapp.repository.BlogRepository;
import com.agamitech.bloggingapp.repository.CommentRepository;
import com.agamitech.bloggingapp.response.Response;
import com.agamitech.bloggingapp.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.agamitech.bloggingapp.constant.Constants.COMMENT_ADDED;
import static com.agamitech.bloggingapp.constant.Constants.INVALID_BLOG_ID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private AuthorRepository authorRepository;


    public ResponseEntity<Response> addComment(CommentDao commentDao) {
        if (blogRepository.existsById(commentDao.getBlogId()) &&
                authorRepository.existsById(commentDao.getUserId())) {
            int id = commentRepository.save(getCommentEntity(commentDao)).getId();
            return new ResponseEntity<>(ResponseUtil.getResponse(COMMENT_ADDED, id), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(ResponseUtil.getResponse(INVALID_BLOG_ID + " " + Constants.INVALID_AUTHOR_ID), HttpStatus.FORBIDDEN);
        }
    }

    private Comment getCommentEntity(CommentDao commentDao) {
        return new Comment(commentDao.getComment(),
                blogRepository.getOne(commentDao.getBlogId()),
                authorRepository.getOne(commentDao.getUserId()));

    }

    public ResponseEntity<List<Comment>> getComment(int blogId) {
        if (blogRepository.existsById(blogId)) {
            return new ResponseEntity<>(commentRepository.findByBlogId(blogId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
    }
}
