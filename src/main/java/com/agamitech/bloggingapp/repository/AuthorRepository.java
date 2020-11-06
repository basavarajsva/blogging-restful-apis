package com.agamitech.bloggingapp.repository;

import com.agamitech.bloggingapp.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Author findByUserName(String userName);
}
