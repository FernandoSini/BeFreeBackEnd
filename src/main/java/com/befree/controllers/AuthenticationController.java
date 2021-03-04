package com.befree.controllers;

import com.befree.data.model.Permission;
import com.befree.data.model.Usertype;
import com.befree.data.model.vo.GraduationVO;
import com.befree.data.model.vo.UserVO;
import com.befree.services.GraduationServices;
import com.befree.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private GraduationServices graduationServices;
    @Autowired
    AuthenticationManager authenticationManager;




    @PostMapping(value = "/login", produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity login(){
        return ResponseEntity.ok().body("teste");
    }

}
