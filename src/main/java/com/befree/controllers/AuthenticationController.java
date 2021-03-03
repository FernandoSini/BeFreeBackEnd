package com.befree.controllers;

import com.befree.data.model.Permission;
import com.befree.data.model.Usertype;
import com.befree.data.model.vo.GraduationVO;
import com.befree.data.model.vo.UserVO;
import com.befree.services.GraduationServices;
import com.befree.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private GraduationServices graduationServices;
    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping(value = "/register", produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public UserVO createUser(@RequestBody UserVO userData) {


        List<GraduationVO> graduations = new ArrayList<>();

        for (GraduationVO graduationVO : userData.getUserGraduations()) {
            System.out.println((graduationVO.getId()));
            var voGraduation = graduationServices.getGraduationById(graduationVO.getId());
            graduations.add(voGraduation);
        }

        userData.setUserName(userData.getUsername());
        userData.setUsertype(Usertype.FREE);
        userData.setUserGraduations(graduations);
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        userData.setPassword(bCrypt.encode(userData.getPassword()));
        userData.setEnabled(true);
        userData.setAccountNonExpired(true);
        userData.setAccountNonLocked(true);
        userData.setCredentialsNonExpired(true);

        Permission permission = new Permission();
        permission.setId(3L);
        List<Permission> permissions = new ArrayList<>();
        permissions.add(permission);
        userData.setPermissions(permissions);


        return userServices.criandoUser(userData);

    }

}
