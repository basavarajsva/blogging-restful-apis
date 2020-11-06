package com.agamitech.bloggingapp.controller;


import com.agamitech.bloggingapp.dto.AuthorDto;
import com.agamitech.bloggingapp.response.Response;
import com.agamitech.bloggingapp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/author")
    public ResponseEntity<Response> createUser(@RequestBody AuthorDto authorDto) {
        return authorService.createUser(authorDto);
    }
}
