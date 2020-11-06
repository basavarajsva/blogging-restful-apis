package com.agamitech.bloggingapp.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Comment {

    Comment() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(nullable = false)
    private String comment;

    //ManyToOne
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Blog blog;

    @OneToOne
    private Author author;

    public Comment(@NotBlank String comment, Blog blog, Author author) {
        this.comment = comment;
        this.blog = blog;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public Blog getBlog() {
        return blog;
    }

    public Author getAuthor() {
        return author;
    }
}
