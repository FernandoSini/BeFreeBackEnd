package com.befree.services;


import com.befree.data.model.User;
import com.befree.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {


    @Autowired
    UserRepository userRepository;


    //criar o user No banco
    public User criandoUser(User user) {
        var entity = userRepository.save(user);
        return entity;
    }

    public List<User> getAllUsers(){
        var entity = userRepository.findAll();
        return entity;
    }



}
