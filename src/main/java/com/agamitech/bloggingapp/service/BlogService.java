package com.agamitech.bloggingapp.service;


import com.agamitech.bloggingapp.dto.BlogDto;
import com.agamitech.bloggingapp.dto.UpdateBlogDto;
import com.agamitech.bloggingapp.model.Blog;
import com.agamitech.bloggingapp.model.Comment;
import com.agamitech.bloggingapp.model.Reaction;
import com.agamitech.bloggingapp.repository.AuthorRepository;
import com.agamitech.bloggingapp.repository.BlogRepository;
import com.agamitech.bloggingapp.repository.CommentRepository;
import com.agamitech.bloggingapp.repository.ReactionRepository;
import com.agamitech.bloggingapp.response.Response;
import com.agamitech.bloggingapp.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.agamitech.bloggingapp.constant.Constants.*;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ReactionRepository reactionRepository;

    public ResponseEntity<Response> createPost(BlogDto blogDto) {
        if (authorRepository.existsById(blogDto.getAuthorId())) {
            int id = blogRepository.save(getBlogEntity(blogDto)).getId();
            return new ResponseEntity<>(ResponseUtil.getResponse(BLOG_CREATED, id), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(ResponseUtil.getResponse(INVALID_AUTHOR_ID), HttpStatus.FORBIDDEN);
        }

    }

    public ResponseEntity<Response> updateBlog(UpdateBlogDto updateBlogDto) {
        Blog blog = blogRepository.getOne(updateBlogDto.getBlogId());
        if (blog != null) {
            blog.setTitle(updateBlogDto.getNewTitle());
            blog.setContent(updateBlogDto.getNewContent());
            blogRepository.save(blog);
            return new ResponseEntity<>(ResponseUtil.getResponse(BLOG_MODIFIED, updateBlogDto.getBlogId()), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(ResponseUtil.getResponse(INVALID_BLOG_ID), HttpStatus.FORBIDDEN);
        }
    }

    public String deleteBlog(int blogId) {
        List<Comment> commentList = commentRepository.findByBlogId(blogId);
        for (Comment c : commentList) {
            List<Reaction> reactions = reactionRepository.findByCommentId(c.getId());
            for (Reaction reaction : reactions) {
                reactionRepository.delete(reaction);
            }
            commentRepository.delete(c);
        }
        Blog blog = blogRepository.getOne(blogId);
        blogRepository.delete(blog);
        return BLOG_DELETED;
    }

    private Blog getBlogEntity(BlogDto blogDto) {
        return new Blog(blogDto.getTitle(), blogDto.getContent(), authorRepository.getOne(blogDto.getAuthorId()));
    }

    public ResponseEntity<List<Blog>> getBlog() {
        return new ResponseEntity<>(blogRepository.findAll(), HttpStatus.OK);
    }
}
