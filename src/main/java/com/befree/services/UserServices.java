package com.befree.services;


import com.befree.data.model.User;
import com.befree.exceptions.ResourceNotFoundException;
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
        var entity = userRepository.saveAndFlush(user);
        return entity;
    }

    //buscando um usuário pelo id
    public User getUserById(String id) {
        var entity = userRepository.findUserById(id);
        return entity;
    }

    public List<User> getAllUsers() {
        var entity = userRepository.findAll();
        return entity;
    }

    public void deleteUser(String userName) {
        User user = userRepository.findOneUserByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);

    }


}
