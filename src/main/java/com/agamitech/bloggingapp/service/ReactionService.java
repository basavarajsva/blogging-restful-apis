package com.agamitech.bloggingapp.service;


import com.agamitech.bloggingapp.dto.ReactionDao;
import com.agamitech.bloggingapp.model.Reaction;
import com.agamitech.bloggingapp.model.Status;
import com.agamitech.bloggingapp.repository.AuthorRepository;
import com.agamitech.bloggingapp.repository.CommentRepository;
import com.agamitech.bloggingapp.repository.ReactionRepository;
import com.agamitech.bloggingapp.response.Response;
import com.agamitech.bloggingapp.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.agamitech.bloggingapp.constant.Constants.*;

@Service
public class ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public ResponseEntity<Response> addReaction(ReactionDao reactionDao) {
        if (commentRepository.existsById(reactionDao.getComment()) &&
                authorRepository.existsById(reactionDao.getAuthor())) {
            int id = reactionRepository.save(getReactionEntity(reactionDao)).getId();
            return new ResponseEntity<>(ResponseUtil.getResponse(REACTION_ADDED, id), HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(ResponseUtil.getResponse(ADD_REACTION_FAILED), HttpStatus.FORBIDDEN);
    }

    private Reaction getReactionEntity(ReactionDao reactionDao) {
        if (reactionDao.getReaction().equals(LIKED)) {
            return new Reaction(commentRepository.getOne(reactionDao.getComment()),
                    authorRepository.getOne(reactionDao.getAuthor()), Status.LIKED);
        } else {
            return new Reaction(commentRepository.getOne(reactionDao.getComment()),
                    authorRepository.getOne(reactionDao.getAuthor()), Status.DISLIKED);
        }
    }

    public int getLikesByBlogId(int blogId) {
        return reactionRepository.getLikeCount(blogId).size();
    }

    public int getDisLikesByBlogId(int blogId) {
        return reactionRepository.getDislikes(blogId).size();
    }
}
