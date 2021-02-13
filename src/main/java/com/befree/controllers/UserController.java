package com.befree.controllers;

import com.befree.data.model.Graduation;
import com.befree.data.model.User;
import com.befree.repository.GraduationRepository;
import com.befree.repository.UserRepository;
import com.befree.services.GraduationServices;
import com.befree.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private GraduationServices graduationServices;

    @PostMapping(value = "/create")
    public User createUser(@RequestBody User userData) {

        Graduation graduation = graduationServices.getGraduationById(1);
        Graduation graduation2 = graduationServices.getGraduationById(2);
        Graduation graduation3 = graduationServices.getGraduationById(3);
        graduation.setUser(userData);
        graduation2.setUser(userData);
        graduation3.setUser(userData);

        List<Graduation>graduationList = new ArrayList<>();
        graduationList.add(graduation);
        graduationList.add(graduation2);
        graduationList.add(graduation3);

        userData.setUserGraduations(graduationList);



       return userServices.criandoUser(userData);

    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") String id){
            User user = userServices.getUserById(id);
        return user;
    }

    @GetMapping(value = "/all")
    public List<User> getAllUsers() {
        List<User> users = userServices.getAllUsers();

        return users;

    }


    @DeleteMapping("/delete/{userName}")
    public ResponseEntity deleteUser(@PathVariable("userName") String userName){
        userServices.deleteUser(userName);
        return ResponseEntity.ok().build();
    }

}
