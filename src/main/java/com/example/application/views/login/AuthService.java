package com.example.application.views.login;


import com.example.application.api.database.User;
import com.example.application.api.database.UserFunction;
import com.example.application.api.database.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final UserRepo userRepo;

    public AuthService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public List<User> findAll(){
        return (List<User>) userRepo.findAll();
    }
    public void register(String username, String password){
        userRepo.save(new User(username, password, UserFunction.Patient));
    }
}
