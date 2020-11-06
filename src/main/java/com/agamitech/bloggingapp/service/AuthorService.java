package com.agamitech.bloggingapp.service;


import com.agamitech.bloggingapp.dto.AuthorDto;
import com.agamitech.bloggingapp.model.Author;
import com.agamitech.bloggingapp.repository.AuthorRepository;
import com.agamitech.bloggingapp.response.Response;
import com.agamitech.bloggingapp.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.agamitech.bloggingapp.constant.Constants.USERNAME_EXIST;
import static com.agamitech.bloggingapp.constant.Constants.USER_ADDED;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public ResponseEntity<Response> createUser(AuthorDto authorDto) {
        if (authorRepository.findByUserName(authorDto.getUserName()) == null) {
            int id = authorRepository.save(getAuthorEntity(authorDto)).getId();
            return new ResponseEntity<>(ResponseUtil.getResponse(USER_ADDED, id), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(ResponseUtil.getResponse(USERNAME_EXIST), HttpStatus.CONFLICT);
        }
    }

    private Author getAuthorEntity(AuthorDto authorDto) {
        return new Author(authorDto.getFirstName(), authorDto.getLastName(), authorDto.getUserName());
    }
}
