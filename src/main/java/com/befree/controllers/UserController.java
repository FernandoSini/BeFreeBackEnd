package com.befree.controllers;

import com.befree.adapter.DozerConverter;
import com.befree.adapter.custom.UserConverter;
import com.befree.data.model.Graduation;
import com.befree.data.model.User;
import com.befree.data.model.Usertype;
import com.befree.data.model.vo.GraduationVO;
import com.befree.data.model.vo.UserVO;
import com.befree.services.GraduationServices;
import com.befree.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private GraduationServices graduationServices;


    //    @PostMapping(value = "/create")
//    public User createUser(@RequestBody User userData) {
////
////        Graduation graduation = graduationServices.getGraduationById(1);
////        Graduation graduation2 = graduationServices.getGraduationById(2);
////        Graduation graduation3 = graduationServices.getGraduationById(3);
////        graduation.setUser(userData);
////        graduation2.setUser(userData);
////        graduation3.setUser(userData);
////
////        List<Graduation>graduationList = new ArrayList<>();
////        graduationList.add(graduation);
////        graduationList.add(graduation2);
////        graduationList.add(graduation3);
////
////        userData.setUserGraduations(graduationList);
//
//
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
//
//        userData.setUsertype(Usertype.FREE);
//        userData.setUserGraduations(graduationList);
//
//        return userServices.criandoUser(userData);
//
//    }
    @PostMapping(value = "/create", produces = {"application/json", "application/xml", "application/x-yaml"},
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
        return userServices.criandoUser(userData);

    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") String id) {
        User user = userServices.getUserById(id);
        return user;
    }

    @GetMapping(value = "/all", produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<UserVO> getAllUsers() {
        List<UserVO> users = userServices.getAllUsers();
        return users;

    }

    @GetMapping(value = "/finduser/{yourName}")
    public ResponseEntity<UserVO> getUserByName(@PathVariable("yourName") String yourName) {
        UserVO user = userServices.getUserByUserName(yourName);
        return ResponseEntity.ok(user);

    }


    @DeleteMapping(value = "/delete/{userName}")
    public ResponseEntity deleteUser(@PathVariable("userName") String userName) {
        userServices.deleteUser(userName);
        return ResponseEntity.ok().build();
    }


}
