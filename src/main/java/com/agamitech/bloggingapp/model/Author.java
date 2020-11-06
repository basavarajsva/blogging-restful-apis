package com.agamitech.bloggingapp.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Author {

    Author() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @NotBlank
    @Column(unique = true)
    private String userName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Author(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }
}
