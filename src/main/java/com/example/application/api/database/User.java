package com.example.application.api.database;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private String password;
    private UserFunction userFunction;

    public User(String name, String password, UserFunction userFunction) {
        this.name = name;
        this.password = password;
        this.userFunction = userFunction;
    }

    public User() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserFunction getUserFunction() {
        return userFunction;
    }

    public void setUserFunction(UserFunction userFunction) {
        this.userFunction = userFunction;
    }
}
