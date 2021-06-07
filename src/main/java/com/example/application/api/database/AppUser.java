package com.example.application.api.database;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AppUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String username;
    private String passwordSalt;
    private String passwordHash;
    private Role role;

    public AppUser(String name, String password, Role role) {
        this.username = name;
        this.passwordSalt = RandomStringUtils.random(32);
        this.role = role;
        this.passwordHash = DigestUtils.sha1Hex(password + passwordSalt);
    }

    public boolean checkPassword(String password){
        return DigestUtils.sha1Hex(password + passwordSalt).equals(passwordHash);
    }

    public AppUser() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
