package com.agamitech.bloggingapp.repository;


import com.agamitech.bloggingapp.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

    Blog findByTitleAndAuthor(String title, int author);

}


