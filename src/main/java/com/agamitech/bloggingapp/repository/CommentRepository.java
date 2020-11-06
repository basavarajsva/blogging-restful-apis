package com.agamitech.bloggingapp.repository;


import com.agamitech.bloggingapp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByBlogId(int blogId);
}
