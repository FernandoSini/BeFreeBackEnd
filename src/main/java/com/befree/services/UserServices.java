package com.befree.services;


import com.befree.data.model.User;
import com.befree.exceptions.CreateUserException;
import com.befree.exceptions.ResourceNotFoundException;
import com.befree.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {


    @Autowired
    private UserRepository userRepository;


    //criar o user No banco
    public User criandoUser(User user) {
        Optional<User> userExists = userRepository.findOneUserByUserName(user.getUserName());
        if (!userExists.isPresent()) {
            var entity = userRepository.saveAndFlush(user);
            return entity;
        } else {
            throw new CreateUserException("User already exist");
        }
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
