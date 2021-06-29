package com.befree.controllers;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.Gender;
import com.befree.data.model.vo.AvatarVO;
import com.befree.data.model.vo.ImageVO;
import com.befree.data.model.vo.UserVO;
import com.befree.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServices userServices;
//    @Autowired
//    private GraduationServices graduationServices;


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


    @GetMapping("/{id}")
    public ResponseEntity<UserVO> getUserById(@PathVariable("id") String id) {
        UserVO userVO = DozerConverter.parseObject(userServices.getUserById(id), UserVO.class);
        return ResponseEntity.ok().body(userVO);
    }

    @GetMapping(value = "/all", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<List<UserVO>> getAllUsers() {
        List<UserVO> users = userServices.getAllUsers();
        return ResponseEntity.ok(users);

    }

    @GetMapping(value = "/finduser/{yourName}",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<UserVO> getUserByName(@PathVariable("yourName") String yourName) {
        UserVO user = userServices.getUserByUserName(yourName);
        return ResponseEntity.ok().body(user);

    }


    @DeleteMapping(value = "/delete/{userName}")
    public ResponseEntity deleteUser(@PathVariable("userName") String userName) {
        userServices.deleteUser(userName);
        return ResponseEntity.ok().body("User deleted successfully");
    }

    @DeleteMapping(value = "/delete/id/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") String id) {
        userServices.deleteById(id);
        return ResponseEntity.ok().body("User Deleted Successfully");
    }

    @PutMapping(value = "/update/{id}",
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<UserVO> updateUser(@PathVariable("id") String id, @RequestBody UserVO userVO) {
        UserVO updatedUser = userServices.update(userVO);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping(value = "/gender/find/different/{gender}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<List<UserVO>> getUsersByGenderDifferentThanYour(@PathVariable("gender") Gender gender) {
        List<UserVO> users = userServices.getUsersByDiferentThenYourGender(gender);
        return ResponseEntity.ok(users);

    }
    @GetMapping(value = "/gender/find/{gender}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<List<UserVO>> getUsersByOneGender(@PathVariable("gender") Gender gender) {
        List<UserVO> users = userServices.getUsersByOneGender(gender);
        return ResponseEntity.ok(users);

    }



}
