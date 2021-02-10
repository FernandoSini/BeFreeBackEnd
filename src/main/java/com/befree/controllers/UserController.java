package com.befree.controllers;

import com.befree.data.model.User;
import com.befree.repository.UserRepository;
import com.befree.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping(value = "/create")
    public User createUser(@RequestBody User userData) {
       return userServices.criandoUser(userData);

    }

    @GetMapping(value = "/all")
    public List<User> getAllUsers() {
        List<User> users = userServices.getAllUsers();

        return users;

    }

}
