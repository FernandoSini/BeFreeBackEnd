package com.befree.controllers;

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


//        Graduation graduation = graduationServices.getGraduationById(1);
//        graduation.getUsers().add(userData);
//
//        Graduation graduation1 = graduationServices.getGraduationById(2);
//        graduation1.getUsers().add(userData);
//        Graduation graduation2 = graduationServices.getGraduationById(3);
//        graduation2.getUsers().add(userData);
//        List<Graduation> graduationList = new ArrayList<>();
//        graduationList.add(graduation);
//        graduationList.add(graduation1);
//        graduationList.add(graduation2);
//        GraduationVO graduationVO = graduationServices.getGraduationById(userData.getGraduations().get(1).getId());
//        graduationVO.getUsers().add(userData);
//        GraduationVO graduationVO2 = graduationServices.getGraduationById(2);
//        graduationVO2.getUsers().add(userData);
//        GraduationVO graduationVO3 = graduationServices.getGraduationById(3);
//        graduationVO3.getUsers().add(userData);
//        for (int i = 0; i <userData.getGraduations().size() ; i++) {
//            GraduationVO graduationVO2 = graduationServices.getGraduationById(userData.getGraduations().get(i).getId())
//        }
        List<GraduationVO> graduations = new ArrayList<>();
//
//        graduations.add(graduationVO);
//        graduations.add(graduationVO2);
//        graduations.add(graduationVO3);
        for (GraduationVO graduationVO : userData.getUserGraduations()
        ) {
            var voGraduation = graduationServices.getGraduationById(graduationVO.getId());
            graduations.add(voGraduation);
        }
        userData.setUsertype(Usertype.FREE);
        userData.setUserGraduations(graduations);
        BCryptPasswordEncoder bCrypt =new  BCryptPasswordEncoder();
        userData.setPassword(bCrypt.encode(userData.getPassword()));
        //userData.setEnabled();


        return userServices.criandoUser(userData);

    }

}
