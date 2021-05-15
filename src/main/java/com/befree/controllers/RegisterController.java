package com.befree.controllers;

import com.befree.data.model.Permission;
import com.befree.data.model.Usertype;
import com.befree.data.model.vo.EventOwnerVO;
import com.befree.data.model.vo.UserVO;
import com.befree.exceptions.AgeException;
import com.befree.services.EventOwnerServices;
import com.befree.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class RegisterController {
    @Autowired
    private UserServices userServices;


    @Autowired
    private EventOwnerServices eventOwnerServices;



    @PostMapping(value = "/register", produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<UserVO> registerUser(@RequestBody UserVO userData) {
        userData.setUserName(userData.getUsername());
        userData.setUsertype(Usertype.FREE);

        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        userData.setPassword(bCrypt.encode(userData.getPassword()));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataConvertida = LocalDate.parse(userData.getBirthday(),df);
        if(LocalDateTime.now().getYear()- dataConvertida.getYear() <18){
                throw new AgeException("Your age must be over 18");
        }
        userData.setBirthday(userData.getBirthday());
        userData.setEnabled(true);
        userData.setAccountNonExpired(true);
        userData.setAccountNonLocked(true);
        userData.setCredentialsNonExpired(true);
        userData.setSchool(userData.getSchool());
        userData.setJob(userData.getJob());
        userData.setLivesIn(userData.getLivesIn());
        userData.setCompany(userData.getCompany());
        userData.setCreatedAt(LocalDateTime.now());
        Permission permission = new Permission();
        permission.setId(3L);
        List<Permission> permissions = new ArrayList<>();
        permissions.add(permission);
        userData.setPermissions(permissions);

        return ResponseEntity.ok().body(userServices.createUser(userData));

    }

    @PostMapping(value = "/register/eventowner", produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public EventOwnerVO registerEventOwner(@RequestBody EventOwnerVO eventOwnerVO){
        EventOwnerVO vo = new EventOwnerVO();
        vo.setDocumentNumber(eventOwnerVO.getDocumentNumber());
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        vo.setPassword(bCrypt.encode(eventOwnerVO.getPassword()));
        vo.setOwnerName(eventOwnerVO.getOwnerName());
        Permission permission = new Permission();
        permission.setId(3L);
        List<Permission> permissions = new ArrayList<>();
        permissions.add(permission);
        vo.setPermissions(permissions);
        vo.setAccountNonExpired(true);
        vo.setAccountNonLocked(true);
        vo.setCredentialsNonExpired(true);
        vo.setEnabled(true);
        vo.setEmail(eventOwnerVO.getEmail());
        vo.setAvatar(eventOwnerVO.getAvatar());
        vo.setCreatedAt(LocalDateTime.now());
        return eventOwnerServices.createOwner(vo);
    }




}
