package com.agamitech.bloggingapp.repository;


import com.agamitech.bloggingapp.model.Comment;
import com.agamitech.bloggingapp.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Integer> {

    List<Reaction> findByCommentId(int commentId);

    @Query(value = "SELECT c,r,b from Comment c,Reaction r,Blog b where c.id = r.comment AND b.id = c.blog AND b.id = ?1 AND r.status = 'LIKED'")
    List<Comment> getLikeCount(int blogId);

    @Query(value = "SELECT c,r,b from Comment c,Reaction r,Blog b where c.id = r.comment AND b.id = c.blog AND b.id = ?1 AND r.status = 'DISLIKED'")
    List<Comment> getDislikes(int blogId);
}
