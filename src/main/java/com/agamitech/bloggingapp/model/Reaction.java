package com.agamitech.bloggingapp.model;


import javax.persistence.*;

@Entity
public class Reaction {

    Reaction(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    private Comment comment;

    @OneToOne
    private Author author;

    @Column(columnDefinition = "enum('LIKED','DISLIKED')")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Reaction(Comment comment, Author author, Status status) {
        this.comment = comment;
        this.author = author;
        this.status = status;
    }

    public int getId() {
        return id;
    }
}

